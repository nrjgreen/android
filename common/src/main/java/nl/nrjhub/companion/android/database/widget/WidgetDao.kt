package nl.nrjhub.companion.android.database.widget

interface WidgetDao {
    suspend fun delete(id: Int)
}
