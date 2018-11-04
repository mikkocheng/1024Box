package stacks;

import cards.Card;

import java.util.ArrayList;
import java.util.List;

public class Stack {

    protected int cursor = 0;
    protected List<Card> cardList = new ArrayList<Card>();

    public int getCursor()
    {
        return cursor;
    }

    public void pushCard(Card newCard)
    {
        cardList.add(newCard);
        cursor++;
    }

    public Card popCard()
    {
        Card toPoped = cardList.get(cursor-1);
        cardList.remove(cursor-1);
        cursor--;
        return toPoped;
    }

    public void mergeCard()
    {
        popCard();
        cardList.get(cursor-1).setCardNum(cardList.get(cursor-1).getCardNum()+1);
    }

    public boolean judge()
    {
        if (cursor>1){
            if(cardList.get(cursor-1).getCardNum() == cardList.get(cursor-2).getCardNum())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }

    }

    public int getTopCardNum()
    {
        return cardList.get(cursor-1).getCardNum();
    }


}