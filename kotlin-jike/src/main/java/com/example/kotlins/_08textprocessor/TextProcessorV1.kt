package com.example.kotlins._08textprocessor


/**
 * 统计单词出现频率：
 * - 目标，输入一段英文句子，统计英文中各单词出现的频率，并根据出现频率的多少进行排序
 * - 操作：
 * --先将句子中的各种标点符号进行替换成空格，采用正则表达式
 * --然后进行分割，以空格进行切分
 * --统计，使用map类结构
 * --最后是统计，使用list集合，以频率为值进行降序统计
 */
class TextProcessorV1 {

    fun processText(text: String): List<WordFreq>? {
        if (text.isEmpty()) {
            return null
        }
        val clean = clean(text)
        val splitList = clean.split(" ")
        val maps = statisText(splitList)
        val resList = reverseText(maps);
        return resList
    }

    /**
     * 文本标点符号清理，正则表达式
     */
    private fun clean(text: String): String {
        return text.replace("[^A-Za-z]".toRegex(), " ").trim()
    }

    /**
     * 统计单词和出现的次数
     */
    private fun statisText(splitList: List<String>): Map<String, Int> {
        val maps = hashMapOf<String, Int>()
        for (text in splitList) {
            text.trim()
            if (text == "") continue
            val size = maps.getOrDefault(text, 0)
            maps[text] = size + 1
        }
        return maps
    }

    /**
     * 按照单词出现的频率进行排序
     */
    private fun reverseText(maps: Map<String, Int>): List<WordFreq> {
        val list = arrayListOf<WordFreq>()
        for (map in maps) {
            list.add(WordFreq(map.key, map.value))
        }
        list.sortByDescending {
            it.frequency
        }
        return list
    }
}

data class WordFreq(val word: String, val frequency: Int)