#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_mklzapp_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject callerObj ) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_azeroth_bwl_PageZLQ_stringFromJNI(JNIEnv *env, jobject instance) {
    return env->NewStringUTF("Hello from C++222");
}extern "C"
JNIEXPORT jint JNICALL
Java_com_azeroth_bwl_PageZLQ_ageGet(JNIEnv *env, jobject instance) {

    return 3;

}