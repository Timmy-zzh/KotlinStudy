package com.example.mylibrary.textprocessor

import com.example.kotlins._08textprocessor.TextProcessorV2
import kotlin.test.Test
import kotlin.test.assertEquals

class TestProcessor {

    @Test
    fun testProcessor() {
        val text = "Kotlin is very good , I lick Kotlin so much"
        val textProcessor = TextProcessorV2()
        val wordFreqs = textProcessor.processText(text)
        assertEquals(2, wordFreqs[0].frequency)
        assertEquals("Kotlin", wordFreqs[0].word)

    }
}