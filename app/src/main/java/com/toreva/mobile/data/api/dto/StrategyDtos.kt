package com.toreva.mobile.data.api.dto

data class StrategiesResponse(val strategies: List<StrategyDto>)
data class StrategyDto(val id: String, val name: String, val status: String, val description: String)
