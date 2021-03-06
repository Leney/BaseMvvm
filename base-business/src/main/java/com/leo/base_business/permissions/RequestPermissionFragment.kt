package com.leo.base_business.permissions

import android.os.Bundle
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.leo.base_business.R
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.PermissionRequest

/**
 *
 * @des:
 * @data: 9/8/20 2:26 PM
 * @Version: 1.0.0
 */
internal class RequestPermissionFragment : Fragment, EasyPermissions.PermissionCallbacks {
    private lateinit var permissions: Array<String>
    private var listener: RequestPermissionsListener? = null
    private val title by lazy {
        arguments?.getString(TITLE) ?: ""
    }
    private val rationale by lazy {
        arguments?.getString(RATIONALE) ?: ""
    }

    companion object {
        private const val INTENT_TO_START = "INTENT_TO_START"
        private const val TITLE = "TITLE"
        private const val RATIONALE = "RATIONALE"
        private const val REQUEST_CODE = 115
        fun newInstance(
            title: String,
            rationale: String,
            vararg permissions: String
        ): RequestPermissionFragment {
            val bundle = Bundle()
            bundle.putStringArray(INTENT_TO_START, permissions)
            bundle.putString(TITLE, title)
            bundle.putString(RATIONALE, rationale)
            val fragment = RequestPermissionFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    constructor() {
        retainInstance = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            permissions = it.getStringArray(INTENT_TO_START) as Array<String>
        }
    }

    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (permissions.isNotEmpty()) {
            initPermission()
        } else {
            removeFragment()
        }
    }

    fun setListener(listener: RequestPermissionsListener): RequestPermissionFragment {
        this.listener = listener
        return this
    }

    private fun removeFragment() {
        fragmentManager?.beginTransaction()?.remove(this)?.commitAllowingStateLoss()
    }

    interface RequestPermissionsListener {
        fun onRequestPermissions(hasPermissions: Boolean, permissions: Array<out String>)
    }

    @AfterPermissionGranted(REQUEST_CODE)
    private fun initPermission() {
        if (EasyPermissions.hasPermissions(requireContext(), *permissions)) {
            listener?.let { it.onRequestPermissions(true, permissions) }
            removeFragment()
        } else {
            EasyPermissions.requestPermissions(
                PermissionRequest.Builder(this, REQUEST_CODE, *permissions)
                    .setRationale(rationale)
                    .setTheme(R.style.Base_Theme_AppCompat_Dialog)
                    .setNegativeButtonText(R.string.business_cancel)
                    .setPositiveButtonText(R.string.business_setting)
                    .build()
            )
        }
    }

    override fun onPermissionsGranted(requestCode: Int, list: List<String>) {
    }

    override fun onPermissionsDenied(requestCode: Int, list: List<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, list)) {
            AppSettingsDialog.Builder(this)
                .setTitle(title)
                .setRationale(rationale)
                .setThemeResId(R.style.Base_Theme_AppCompat_Dialog)
                .setNegativeButton(R.string.business_cancel)
                .setPositiveButton(R.string.business_setting)
                .build()
                .show()
        }
        removeFragment()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }
}