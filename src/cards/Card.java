package cards;

public class Card {

    protected int cardNum;
    protected int imgUrl;

    public Card(int cardNum, int imgUrl)
    {
        this.cardNum = cardNum;
        this.imgUrl = imgUrl;
    }


    public Card(int cardNum)
    {
        this.cardNum = cardNum;
    }

    public int getCardNum()
    {
        return cardNum;
    }

    public void setImgUrl(int imgUrl)
    {
        this.imgUrl = imgUrl;
    }

    public void setCardNum(int cardNum)
    {
        this.cardNum = cardNum;
    }

}
