#ifndef FORMATTER_COMMON_UTILS_ANDROID_UTILS_H_
#define FORMATTER_COMMON_UTILS_ANDROID_UTILS_H_

#include <jni.h>

#include <string>
#include <vector>

class JavaClass;
class JavaArray;
class Constructor;
class ObjectField;
class VoidMethod;
class IntMethod;
class LongMethod;
class BooleanMethod;
class StringMethod;
class ObjectMethod;
class ObjectArrayMethod;
class StaticObjectMethod;

namespace common {

class JString {

public:
  JString(JNIEnv* env, const std::string &str, bool emptyIsNull = true);
  jstring j_string();
  ~JString();

private:
  JString(const JString&);
  const JString& operator = (const JString&);

private:
  JNIEnv *env_；
  jstring j_string_;

}


inline jstring JString::j_string() {
    return j_string_;
}

} // namespace common
#endif // FORMATTER_COMMON_UTILS_ANDROID_UTILS_H_

