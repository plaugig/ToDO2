package com.plaugig.todo2.domain.options.priority

import javax.inject.Inject

class OptionsPriorityTypeMapper @Inject constructor() {

    fun map(type: OptionsPriorityType?): Int? {
        type ?: return null

        return when (type) {
            OptionsPriorityType.Hard -> 0
            OptionsPriorityType.Medium -> 1
            OptionsPriorityType.Ez -> 2
        }
    }

    fun mapReverse(id: Int?): OptionsPriorityType? {
        id ?: return null

        return when (id) {
            0 -> OptionsPriorityType.Hard
            1 -> OptionsPriorityType.Medium
            2 -> OptionsPriorityType.Ez
            else -> error("Invalid id: $id for type.!.")
        }
    }
}