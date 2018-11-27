package com.dpancerz.chess

enum class MoveStrength(val suffix: String) {
    REGULAR(""),
    GOOD("!"),
    EXCELLENT("!!"),
    MISTAKE("?"),
    BLUNDER("??"),
    INTERESTING("!?"),
    DUBIOUS("?!"),
    NOVELTY("TN")
}