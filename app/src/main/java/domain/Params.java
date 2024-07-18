package domain;


import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Params {
  public static final Params EMPTY = Params.create();

  private final Map<String, Object> parameters = new HashMap<>();

  private Params() {}

  public static Params create() {
    return new Params();
  }

  public void putInt(@NonNull String key,
                     @NonNull int value) {
    parameters.put(key, value);
  }

  public int getInt(@Nullable String key,
                    @Nullable int defaultValue) {
    final Object object = parameters.get(key);
    if (object == null) {
      return defaultValue;
    }
    try {
      return (int) object;
    } catch (ClassCastException e) {
      return defaultValue;
    }
  }

  public void putString(@NonNull String key,
                        @NonNull String value) {
    parameters.put(key, value);
  }

  public String getString(@NonNull String key,
                          @Nullable String defaultValue) {
    final Object object = parameters.get(key);
    if (object == null) {
      return defaultValue;
    }
    try {
      return (String) object;
    } catch (ClassCastException e) {
      return defaultValue;
    }
  }

  public void putBoolean(@NonNull String key,
                        @NonNull Boolean value) {
    parameters.put(key, value);
  }

  public Boolean getBoolean(@NonNull String key) {
    final Object object = parameters.get(key);
    if (object == null) {
      return false;
    }
    try {
      return (Boolean) object;
    } catch (ClassCastException e) {
      return false;
    }
  }

  public void putDouble(@NonNull String key,
                        @NonNull Double value) {
    parameters.put(key, value);
  }

  public Double getDouble(@NonNull String key) {
    final Object object = parameters.get(key);
    if (object == null) {
      return 0.0;
    }
    try {
      return (Double) object;
    } catch (ClassCastException e) {
      return 0.0;
    }
  }

  public void putLong(@NonNull String key,
                      @NonNull long value) {
    parameters.put(key, value);
  }


  public void putListString(@NonNull String key,
                        @NonNull List<String> value) {
    parameters.put(key, value);
  }

  public List<String> getListString(@NonNull String key) {
    List<String> defaultValue = new ArrayList<>();
    final Object object = parameters.get(key);
    if (object == null) {
      return defaultValue;
    }
    try {
      return  (List<String>) object;
    } catch (ClassCastException e) {
      return defaultValue;
    }
  }

  public long getLong(@NonNull String key,
                        @Nullable long defaultValue) {
    final Object object = parameters.get(key);
    if (object == null) {
      return defaultValue;
    }
    try {
      return (long) object;
    } catch (ClassCastException e) {
      return defaultValue;
    }
  }


  public void putUri(@NonNull String key,
                      @NonNull Uri value) {
    parameters.put(key, value);
  }


  public Uri getUri(@NonNull String key) {
    final Object object = parameters.get(key);
    try {
      return (Uri) object;
    } catch (ClassCastException e) {
      return null;
    }
  }


}