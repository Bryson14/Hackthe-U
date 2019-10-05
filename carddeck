import random


class Deck:
    def __init__(self):
        self.shuffle()

    def shuffle(self):
        self.__deck = []
        for i in range(52):
            self.__deck.append(Card(i))
        random.shuffle(self.__deck)

    def draw(self):
        return self.__deck.pop()


class Card:
    def __init__(self, value):
        self.__value = value
        self.__suits = ["Spades", "Clubs", "Hearts", "Diamonds"]
        self.__ranks = ["Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen",
                        "King"]

    def getRank(self):
        return self.__ranks[self.__value % 13]

    def getSuit(self):
        return self.__suits[self.__value // 13]

    def getCardValue(self):
        return self.__value % 13 + 1

    def getDeckValue(self):
        return self.__value

    def getNickName(self):
        nickName = ""
        if self.getCardValue() > 1 and self.getCardValue() < 11:
            nickName += str(self.getCardValue())
        else:
            nickName += self.getRank()[0]

        nickName += self.getSuit()[0]

        return nickName

    def __str__(self):
        return self.getRank() + " of " + self.getSuit()

    def __repr__(self):
        return self.__str__()

