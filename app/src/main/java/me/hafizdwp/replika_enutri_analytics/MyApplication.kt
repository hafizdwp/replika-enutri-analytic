package me.hafizdwp.replika_enutri_analytics

import androidx.multidex.MultiDexApplication
import com.qiscus.jupuk.Jupuk
import com.qiscus.nirmana.Nirmana
import com.qiscus.sdk.chat.core.QiscusCore
import com.vanniktech.emoji.EmojiManager
import com.vanniktech.emoji.one.EmojiOneProvider

/**
 * @author hafizdwp
 * 06/07/2020
 **/
class MyApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        Nirmana.init(this)
        QiscusCore.setup(this, BuildConfig.QISCUS_SDK_APP_ID)
        QiscusCore.getChatConfig()
            .enableDebugMode(true)
//            .setNotificationListener(PushNotificationUtil::showNotification).isEnableFcmPushNotification = true
        EmojiManager.install(EmojiOneProvider())
        Jupuk.init(this)
    }
}