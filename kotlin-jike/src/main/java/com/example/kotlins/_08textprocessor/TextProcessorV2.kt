package com.example.kotlins._08textprocessor


/**
 * 统计单词出现频率：
 * - 目标，输入一段英文句子，统计英文中各单词出现的频率，并根据出现频率的多少进行排序
 * - 操作：
 * --先将句子中的各种标点符号进行替换成空格，采用正则表达式
 * --然后进行分割，以空格进行切分
 * --统计，使用map类结构
 * --最后是统计，使用list集合，以频率为值进行降序统计
 * - 版本二：采用扩展函数和告诫函数实现
 */
class TextProcessorV2 {

    fun processText(text: String): List<WordFreq2> {
//        val clean = clean(text)
//        val splitList = clean.split(" ")
//        val maps = statisText(splitList)
//        val resList = reverseText(maps)

        return text
            .clear()
            .split(" ")
            .getWordCount()
            .mapToList { WordFreq2(it.key, it.value) }
            .sortedByDescending { it.frequency }
    }

//    /**
//     * 文本标点符号清理，正则表达式
//     */
//    private fun clean(text: String): String {
//        return text.replace("[^A-Za-z]".toRegex(), " ").trim()
//    }
//
//    /**
//     * 统计单词和出现的次数
//     */
//    private fun statisText(splitList: List<String>): Map<String, Int> {
//        val maps = hashMapOf<String, Int>()
//        for (text in splitList) {
//            text.trim()
//            if (text == "") continue
//            val size = maps.getOrDefault(text, 0)
//            maps[text] = size + 1
//        }
//        return maps
//    }
//
//    /**
//     * 按照单词出现的频率进行排序
//     */
//    private fun reverseText(maps: Map<String, Int>): List<WordFreq2> {
//        val list = arrayListOf<WordFreq2>()
//        for (map in maps) {
//            list.add(WordFreq2(map.key, map.value))
//        }
//        list.sortByDescending {
//            it.frequency
//        }
//        return list
//    }
}


/**
 * 将map的<Key,Value>单个元素，转换成List的元素
 * -高阶函数，传入函数类型
 */
private fun <T> Map<String, Int>.mapToList(transform: ((Map.Entry<String, Int>) -> T)): List<T> {
    val list = arrayListOf<T>()
    for (map in this) {
        val value = transform(map)
        list.add(value)
    }
    return list
}

private inline fun <T> Map<String, Int>.mapToList2(transform: ((Map.Entry<String, Int>) -> T)): List<T> {
    val list = arrayListOf<T>()
    for (map in this) {
        val value = transform(map)
        list.add(value)
    }
    return list
}

private fun List<String>.getWordCount(): Map<String, Int> {
    val maps = hashMapOf<String, Int>()
    for (text in this) {
        text.trim()
        if (text == "") continue
        val size = maps.getOrDefault(text, 0)
        maps[text] = size + 1
    }
    return maps
}

private fun String.clear(): String {
    return this.replace("[^A-Za-z]".toRegex(), " ").trim()
}

data class WordFreq2(val word: String, val frequency: Int)