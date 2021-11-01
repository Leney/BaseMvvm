package com.leo.base_business.permissions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.leo.base_business.R
import com.leo.mvvm.utils.String
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 *
 * @des:
 * @data: 9/8/20 2:27 PM
 * @Version: 1.0.0
 */
suspend fun FragmentActivity.requestPermissionsForResult(
    vararg permissions: String,
    title: String = baseContext.String(R.string.business_request_permissions),
    rationale: String = baseContext.String(R.string.business_need_allow_permissions),
): Boolean =
    suspendCancellableCoroutine {
        InlinePermissionResult(this)
            .onSuccess(object : SuccessCallback {
                override fun onSuccess() {
                    it.resume(true)
                }
            })
            .onFail(object : FailCallback {
                override fun onFailed() {
                    it.resumeWithException(InlineRequestPermissionException())
                }
            })
            .requestPermissions(title = title, rationale = rationale, permissions = *permissions)
    }


suspend fun Fragment.requestPermissionsForResult(
    vararg permissions: String,
    title: String = context?.String(R.string.business_request_permissions) ?: "",
    rationale: String = context?.String(R.string.business_need_allow_permissions) ?: "",
): Boolean =
    suspendCancellableCoroutine {
        InlinePermissionResult(this)
            .onSuccess(object : SuccessCallback {
                override fun onSuccess() {
                    it.resume(true)
                }
            })
            .onFail(object : FailCallback {
                override fun onFailed() {
                    it.resumeWithException(InlineRequestPermissionException())
                }
            })
            .requestPermissions(title = title, rationale = rationale, permissions = *permissions)
    }