package io.github.a2kaido.moshi.ui

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ScreenshotDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("スクショ")
            .setMessage("スクショテスト")
            .setPositiveButton("閉じる", null)
            .create()
    }
}