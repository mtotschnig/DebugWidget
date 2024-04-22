package com.example.debugwidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetManager.OPTION_APPWIDGET_MAX_WIDTH
import android.appwidget.AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.os.Bundle
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */
class DebugWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onAppWidgetOptionsChanged(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int,
        newOptions: Bundle?
    ) {
        updateAppWidget(context, appWidgetManager, appWidgetId)
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val widgetText = availableWidth(appWidgetManager, appWidgetId);
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.debug_widget)
    views.setTextViewText(R.id.appwidget_text, widgetText.toString())

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}

fun availableWidth(
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) = with(appWidgetManager.getAppWidgetOptions(appWidgetId)) {
    getInt(OPTION_APPWIDGET_MIN_WIDTH) to getInt(OPTION_APPWIDGET_MAX_WIDTH)
}