package ru.admitad.admitadsdk;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static ru.admitad.admitadsdk.AdmitadTrackerCode.ERROR_GENERIC;
import static ru.admitad.admitadsdk.AdmitadTrackerCode.ERROR_NO_INTERNET;
import static ru.admitad.admitadsdk.AdmitadTrackerCode.ERROR_SDK_ADMITAD_UID_MISSED;
import static ru.admitad.admitadsdk.AdmitadTrackerCode.ERROR_SDK_GAID_MISSED;
import static ru.admitad.admitadsdk.AdmitadTrackerCode.ERROR_SDK_NOT_INITIALIZED;
import static ru.admitad.admitadsdk.AdmitadTrackerCode.ERROR_SERVER_UNAVAILABLE;
import static ru.admitad.admitadsdk.AdmitadTrackerCode.NONE;
import static ru.admitad.admitadsdk.AdmitadTrackerCode.SUCCESS;

@IntDef({NONE, SUCCESS, ERROR_GENERIC, ERROR_NO_INTERNET, ERROR_SERVER_UNAVAILABLE, ERROR_SDK_NOT_INITIALIZED, ERROR_SDK_GAID_MISSED, ERROR_SDK_ADMITAD_UID_MISSED})
@Retention(RetentionPolicy.SOURCE)
public @interface AdmitadTrackerCode {
    int NONE = 0;
    int SUCCESS = 200;

    int ERROR_GENERIC = -100;
    int ERROR_NO_INTERNET = -200;
    int ERROR_SERVER_UNAVAILABLE = -1;
    int ERROR_SDK_NOT_INITIALIZED = -1000;
    int ERROR_SDK_GAID_MISSED = -1200;
    int ERROR_SDK_ADMITAD_UID_MISSED = -1300;
}
