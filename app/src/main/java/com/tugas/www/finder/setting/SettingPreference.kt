package com.tugas.www.finder.setting

import android.content.Context

class SettingPreference(context: Context) {
    companion object {
        private const val EXPENSE_LIMIT = "expense_limit"
        private const val PLAN = "plan"
        private const val IS_PASS_CODE = "isPassCode"
        private const val PASS_CODE = "pass_code"
    }

    private val preference = context.getSharedPreferences("setting_prefs", Context.MODE_PRIVATE)

    fun setExpenseLimitNotifSetting(state: Boolean) {
        val editor = preference.edit()
        editor.putBoolean(EXPENSE_LIMIT, state)
        editor.apply()
    }

    fun setPlanNotificationSetting(state: Boolean) {
        val  editor = preference.edit()
        editor.putBoolean(PLAN, state)
        editor.apply()
    }

    fun setPassCodeState(state: Boolean) {
        val  editor = preference.edit()
        editor.putBoolean(IS_PASS_CODE, state)
        editor.apply()
    }

    fun setPassCodeValue(value: Int) {
        val  editor = preference.edit()
        editor.putInt(PASS_CODE, value)
        editor.apply()
    }

    fun getExpenseLimitNotifSetting(): Boolean {
        return preference.getBoolean(EXPENSE_LIMIT, false)
    }

    fun getPlanNotificationSetting(): Boolean {
        return preference.getBoolean(PLAN, false)
    }

    fun getPassCodeState(): Boolean {
        return preference.getBoolean(IS_PASS_CODE, false)
    }

    fun getPassCodeValue(): Int {
        return preference.getInt(PASS_CODE, 0)
    }
}