package com.example.myapplication

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class MainViewModel : ViewModel() {
    private val _selectedDate = MutableStateFlow(LocalDate.now())
    val selectedDate: StateFlow<LocalDate> = _selectedDate.asStateFlow()

    private val _selectedTime = MutableStateFlow(LocalTime.now())
    val selectedTime: StateFlow<LocalTime> = _selectedTime.asStateFlow()

    private val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    val formattedDate: String
        get() = _selectedDate.value.format(dateFormatter)

    val formattedTime: String
        get() = _selectedTime.value.format(timeFormatter)

    fun updateDate(millis: Long?) {
        millis?.let {
            _selectedDate.value = Instant.ofEpochMilli(it)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
        }
    }

    fun updateTime(hour: Int, minute: Int) {
        _selectedTime.value = LocalTime.of(hour, minute)
    }
}
