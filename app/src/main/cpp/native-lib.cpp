#include <jni.h>
#include <string>
#include <ifaddrs.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <android/log.h>
#include <unistd.h>
#include <netdb.h>
#include <vector>


#ifndef IN6_IS_ADDR_GLOBAL
#define IN6_IS_ADDR_GLOBAL(a) \
    ((((const uint32_t *) (a))[0] & htonl((uint32_t)0x70000000)) \
     == htonl((uint32_t)0x20000000))
#endif

#define LOG_TAG "NativeLib"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

extern "C"
JNIEXPORT jstring JNICALL
Java_presentation_views_MainActivity_getIPAddress(JNIEnv* env, jobject /* this */) {
    struct ifaddrs* ifaddr;
    struct ifaddrs* ifa;
    std::vector<std::string> ipv4_addresses;
    std::vector<std::string> ipv6_addresses;

    if (getifaddrs(&ifaddr) == -1) {
        return env->NewStringUTF("");
    }

    for (ifa = ifaddr; ifa != nullptr; ifa = ifa->ifa_next) {
        if (ifa->ifa_addr == nullptr) continue;

        int family = ifa->ifa_addr->sa_family;
        if (family == AF_INET || family == AF_INET6) {
            char host[NI_MAXHOST];
            if (getnameinfo(ifa->ifa_addr,
                            (family == AF_INET) ? sizeof(struct sockaddr_in) : sizeof(struct sockaddr_in6),
                            host, NI_MAXHOST, nullptr, 0, NI_NUMERICHOST) != 0) {
                continue;
            }

            std::string ifa_name(ifa->ifa_name);
            std::string ip_address(host);
            LOGI("Interface: %s, Address: %s", ifa_name.c_str(), ip_address.c_str());

            if (family == AF_INET6) {
                struct sockaddr_in6* ipv6 = (struct sockaddr_in6*)ifa->ifa_addr;
                if (IN6_IS_ADDR_GLOBAL(&ipv6->sin6_addr)) {

                    freeifaddrs(ifaddr);
                    return env->NewStringUTF(ip_address.c_str());
                }
                ipv6_addresses.push_back(ip_address);
            } else if (family == AF_INET) {
                struct sockaddr_in* ipv4 = (struct sockaddr_in*)ifa->ifa_addr;
                if (!(ipv4->sin_addr.s_addr == INADDR_ANY || ipv4->sin_addr.s_addr == INADDR_BROADCAST ||
                      (ipv4->sin_addr.s_addr & htonl(0x000000ff)) == htonl(0x0000007f) || // 127.0.0.1/8 loopback
                      (ipv4->sin_addr.s_addr & htonl(0x0000ffff)) == htonl(0x0000a9fe))) { // 169.254.0.0/16 link-local
                    if ((ipv4->sin_addr.s_addr & htonl(0xff000000)) == htonl(0x0a000000) || // 10.0.0.0/8
                        (ipv4->sin_addr.s_addr & htonl(0xfff00000)) == htonl(0xac100000) || // 172.16.0.0/12
                        (ipv4->sin_addr.s_addr & htonl(0xffff0000)) == htonl(0xc0a80000)) { // 192.168.0.0/16

                        continue;
                    } else {
                        freeifaddrs(ifaddr);
                        return env->NewStringUTF(ip_address.c_str());
                    }
                }
                ipv4_addresses.push_back(ip_address);
            }
        }
    }

    freeifaddrs(ifaddr);

    // Aplicar la regla c si no hay IPs que cumplan con las reglas a o b
    if (!ipv4_addresses.empty()) {
        return env->NewStringUTF(ipv4_addresses.front().c_str());
    } else if (!ipv6_addresses.empty()) {
        return env->NewStringUTF(ipv6_addresses.front().c_str());
    }

    return env->NewStringUTF("");
}