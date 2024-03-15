using System.Data;

namespace GildedRoseKata;

public abstract class DailyUpdater
{
    public void DailyUpdate(Item item)
    {
        UpdateSellIn(item);
        UpdateQuality(item);
        if (IsExpired(item))
        {
            UpdateQuality(item);
        }
    }

    public abstract void UpdateQuality(Item item);
    public virtual void UpdateSellIn(Item item) => item.SellIn -= 1;

    protected static bool IsExpired(Item item) => item.SellIn < 0;

    protected static void IncreaseQuality(Item item, int byValue = 1)
    {
        item.Quality = int.Min(item.Quality + byValue, ItemQuality.MaxQuality);
    }

    protected static void DecreaseQuality(Item item, int byValue = 1)
    {
        item.Quality = int.Max(item.Quality - byValue, ItemQuality.MinQuality);
    }
}

public class DailyUpdaterForRegularItems : DailyUpdater
{
    public override void UpdateQuality(Item item)
    {
        DecreaseQuality(item);
    }
}

public class DailyUpdaterForBetterWithAgeItems : DailyUpdater
{
    public override void UpdateQuality(Item item)
    {
        IncreaseQuality(item);
    }
}


public class DailyUpdaterForBackstagePassesItems : DailyUpdater
{
    public override void UpdateQuality(Item item)
    {
        if (item.SellIn > 9)
        {
            IncreaseQuality(item);
        }
        else if (item.SellIn > 4)
        {
            IncreaseQuality(item, 2);
        }
        else if (!IsExpired(item))
        {
            IncreaseQuality(item, 3);
        }
        else //Expired
        {
            DecreaseQuality(item, item.Quality);
        }
    }
}

public class DailyUpdaterForLegendaryItems : DailyUpdater
{
    public override void UpdateSellIn(Item item)
    {
        // Legendary Items don't change over time
    }
    public override void UpdateQuality(Item item)
    {
        // Legendary Items don't change over time
    }
}


