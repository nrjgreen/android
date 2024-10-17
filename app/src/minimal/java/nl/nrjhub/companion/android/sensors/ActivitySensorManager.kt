package nl.nrjhub.companion.android.sensors

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import nl.nrjhub.companion.android.common.R as commonR
import nl.nrjhub.companion.android.common.sensors.SensorManager

class ActivitySensorManager : BroadcastReceiver(), SensorManager {

    override fun onReceive(context: Context, intent: Intent) {
        // Noop
    }
    override val name: Int
        get() = commonR.string.sensor_name_activity

    override suspend fun getAvailableSensors(context: Context): List<SensorManager.BasicSensor> {
        return listOf()
    }

    override fun requiredPermissions(sensorId: String): Array<String> {
        // Noop
        return emptyArray()
    }

    override fun requestSensorUpdate(context: Context) {
        // Noop
    }
}
