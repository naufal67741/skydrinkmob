package com.example.skydrinkmob.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.skydrinkmob.LoginActivity;
import com.example.skydrinkmob.model.Cart;
import com.example.skydrinkmob.model.Drink;
import com.example.skydrinkmob.model.Transaction;
import com.example.skydrinkmob.model.User;

import java.util.Vector;

public class MyDB {
    //    public static String result = "";

    public static String DATABASE_NAME_DRINK = "MusicTable";
    public static String DATABASE_NAME_TRANSACTION = "TransactionTable";
    public static String DATABASE_NAME_USER = "UserTable";
    public static String DATABASE_NAME_CART = "CartTable";
    public static Vector<Drink> vDrinks = new Vector<>();
    public static Vector<Drink> vFavDrinks = new Vector<>();
    public static Vector<Cart> vCarts = new Vector<>();
    public static Vector<User> vUsers = new Vector<>();
    public static Vector<Transaction> vTransactions = new Vector<>();
    public static boolean isDataInserted = false;
    public static Transaction lastTransaction;
    public static Context ctx;
    //    private static RequestQueue queue;
    public static void insertDrinkData(String DrinkName, String DrinkDescription, int DrinkPrice, int DrinkSweetness, int DrinkSpicy, int DrinkMalty, int ClusterID, Context ctx) {
        DBHelper helper = new DBHelper(ctx);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("DrinkName", DrinkName);
        cv.put("DrinkDescription", DrinkDescription);
        cv.put("DrinkPrice", DrinkPrice);
        cv.put("DrinkSweetness", DrinkSweetness);
        cv.put("DrinkSpicy", DrinkSpicy);
        cv.put("DrinkMalty", DrinkMalty);
        cv.put("ClusterID", ClusterID);

        db.insert(DATABASE_NAME_DRINK, null, cv);
        db.close();

    }
    public static void insertDrinkDataModel(Drink drink , Context ctx) {

        DBHelper helper = new DBHelper(ctx);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("DrinkName", drink.getDrinkName());
        cv.put("DrinkDescription", drink.getDrinkDescription());
        cv.put("DrinkPrice", drink.getDrinkPrice());
        cv.put("DrinkSweetness", drink.getDrinkSweetness());
        cv.put("DrinkSpicy", drink.getDrinkSpicy());
        cv.put("DrinkMalty", drink.getDrinkMalty());
        cv.put("ClusterID", drink.getClusterID());

        db.insert(DATABASE_NAME_DRINK, null, cv);
        db.close();

    }

    public static void initializeMusic(Context ctx){
        DBHelper helper = new DBHelper(ctx);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT * FROM " + DATABASE_NAME_DRINK;
        Cursor c = db.rawQuery(sql, null);
        if(c.moveToNext()){
            isDataInserted = true;
        }else{
            insertDrinkData("Aberfeldy","A fruity, clean and polished malt with a touch of honey and spice, Aberfeldy 12 Year Old is an excellent introduction to this Highland distillery. Aberfeldy's main claim to fame is as the heart of the excellent Dewar's blend but whiskies like this are putting it firmly in the spotlight.",230,2,1,2,1,ctx);
            insertDrinkData("Aberlour","All our single malts are made with the best local ingredients of barley and soft water from nearby mountain, Ben Rinnes. Our expertly crafted new spirit is usually matured for a minimum of 12 years both in the finest hand-picked Oloroso Sherry butts and American Oak barrels. It’s this double cask maturation which subtly brings together the characteristics of each cask to create a truly rich and rewardingly complex range of single malts.",180,3,3,3,4,ctx);
            insertDrinkData("AnCnoc","Highland single malt scotch whisky anCnoc is a rather curious whisky. You see, this fine single malt is created using traditional production methods. It's just that we use them to make a refreshingly modern whisky (something that's reflected in our contemporary bottle design and packaging today).",220,3,0,2,3,ctx);
            insertDrinkData("Ardbeg","As a replacement for the much-loved 1990 Airigh nam Beist, Ardbeg Corryvreckan had some pretty big shoes to fill, but the good news is that this is a belter, winning World's Best Single Malt Whisky at the World Whisky Awards 2010 and Best No Age Statement Scotch from Jim Murray's Whisky Bible.",170,1,2,2,2,ctx);
            insertDrinkData("Ardmore","Ardmore distillery is a single malt Scotch whisky distillery, located in the village of Kennethmont, Aberdeenshire. ... Ardmore Traditional Cask was the distillery's single malt. It was bottled at 46% ABV, in bottles embossed with an image of a golden eagle.",30,2,1,3,1,ctx);
            insertDrinkData("ArranIsleOf","The initial rush of rich vanilla sweetness gives way to a touch of cinnamon which adds a spicy edge to the soft and sweet texture. The classic Arran citrus notes have rounded with age and reveal new depths of character against a background of sweet oak.",190,3,1,1,3,ctx);
            insertDrinkData("Auchentoshan","This Auchentoshan 12-year-old expression replaced the old 10 year-old when the range was totally revamped. Elegant and refined, with those classic Auchentoshan notes of vanilla and almonds.",130,2,1,2,1,ctx);
            insertDrinkData("Auchroisk","Made in 2007 at Auchriosk distillery, this Speyside single malt was matured in a single cask for 11 years, before being bottled in August 2018 by indie bottler Mossburn for its Vintage Casks series.",270,3,1,2,3,ctx);
            insertDrinkData("Aultmore","Part of Dewar's The Last Great Malts range launched in 2014, this 12-year-old Aultmore is the first release from the distillery for a few years and is elegant, light and fruity.",60,2,0,2,1,ctx);
            insertDrinkData("Balblair","The 2005 vintage of Balblair is a light, fruity and refreshing dram that's typical of the Highland distillery's style. Matured in casks that previously held bourbon, this single malt has notes of toffee and vanilla alongside orchard citrus and ground spice.",210,3,2,1,4,ctx);
            insertDrinkData("Balmenach","Rich and fruity whisky from Balmenach, bottled after long ageing as part of the, appropriately titled, Old & Rare range by Hunter Laing. This takes the distillery's classic Speyside character and amplifies it, layering rich, winter fruit with peppery spice and fresh citrus.",200,3,1,0,3,ctx);
            insertDrinkData("Belvenie","A 14-year-old limited-edition Balvenie which finished its maturation in casks which had previously held Caribbean rum. Expect vanilla and toffee notes as the rum contributes extra sweetness to the flavours.",160,2,2,2,2,ctx);
            insertDrinkData("BenNevis","Made at Ben Nevis distillery, this 23-year-old single malt from That Boutique-y Whisky Company has been matured in a single cask, before being bottled in 2019. Aromas of cereals, candied lemon peel, raspberry liquorice and dried fruits compliment notes of raisins, sultanas, garden herbs and biscuits that fill the palate.",300,2,2,2,2,ctx);
            insertDrinkData("Benriach","The very first batch of Benriach's Peated Cask Strength series has been matured in a combination of oloroso-sherry and bourbon casks. Warming and toasty with notes of sweet toffee apples, nectarines, tablet and heather smoke.",30,2,2,2,2,ctx);
            insertDrinkData("Benrinnes","A 1997 Benrinnes single malt, matured in a single cask for 21 years before being bottled by Daily Drams in 2019, as part of its The Nectar of The Daily Drams.",230,2,1,3,1,ctx);
            insertDrinkData("Benromach","Very few malts can stand anything like as much ageing as this Benromach has managed. Which makes it all the more amazing that the whisky inside the bottle can still seem so fresh. Of course, over fifty-five years, the angels get to take more than their fair share - only eighty-three bottles were yielded from the cask - but what remains is a fantastic concentration of flavour and an almost eerie youthfulness.",270,2,2,2,2,ctx);
            insertDrinkData("Bladnoch","Celebrating the 200th anniversary of Bladnoch's foundation, this is a richly sherried 29 Year Old whisky that spent its last 18 months of maturation in Moscatel casks. It was formed from a marriage of two casks, both distilled in 1988, and has notes of sherry, acacia, sweet oak, citrus and dark chocolate.Each of the 200 bottles released comes in a special presentation case and is crowned with a gold stopper designed by the distillery's Australian owner David Prior.",220,2,1,2,1,ctx);
            insertDrinkData("BlairAthol","The Rare Malts are a now-discontinued series of cask-strength releases designed by Diageo to showcase some of the hidden treasures in their portfolio. This is a fine cask-strength malt from a great, though little-known, Highland distillery.",30,2,2,2,2,ctx);
            insertDrinkData("Bowmore","Originally exclusive to travel retail, this 18 Year Old Bowmore is, as the name suggests, a 'deep and complex' Islay single malt. Matured in oloroso and Pedro Ximénez sherry casks, it has rich notes of chocolate, peat-smoke roasted coffee, orange peel and treacle toffee.",120,2,2,1,2,ctx);
            insertDrinkData("Bruichladdich","The very last 22 casks from 1985 have been used to create this fantastic edition of Bruichladdich's single malt. The casks were rediscovered in 2012 by then master distiller Jim McEwan, who filled their spirit into fresh bourbon barrels for added depth and complexity. In 2017 the whisky was again rehomed, this time into French oak which had previously held claret from a prestigious chateau. This three month period imbued the bourbon-aged whisky with an extra layer of fruit and sweet notes, as well as a subtle blush colour.",220,1,2,2,2,ctx);
            insertDrinkData("Bunnahabhain","A limited-edition 1988 Bunnahabhain single malt that has been finished in a quartet of ex-Marsala hogsheads for three years before being bottled in January 2019. Rich oaky aromas fill the nose, alongside sweet fruit, Marsala, honeyed nuts, berries, floral heather and candied fruit that are echoed in the palate.",160,2,1,2,1,ctx);
            insertDrinkData("Caol Ila","Made in 2001 at Caol Ila distillery, this Islay single malt was matured in a single American oak hogshead until being bottled in April 2019 for Gordon & MacPhail's Connoisseurs Choice series. Subtle, smoky aromas of fresh citrus, green apple and creamy vanilla fill the nose, and the palate offers notes of bonfire smoke, bright citrus and crunchy apples.",180,1,2,1,2,ctx);
            insertDrinkData("Cardhu","A bottling of 1973 vintage Cardhu released as part of Diageo's Rare Malts range around the turn of the millenium. A 27 year old dram bottled at a fairly fierce 60.02% ABV.",280,3,1,2,3,ctx);
            insertDrinkData("Clynelish","Part of Diageo's Special Releases 2015. A blend of four different ages, this Clynelish has that trademark rich waxiness, along with refreshing green apple, lemon and grapefruit notes and a creamy mouthfeel.",100,2,2,1,2,ctx);
            insertDrinkData("Craigallechie","Well-aged whisky from Craigellachie, combining the distillery's famously heavy character with elegant orchard fruit and sherry-cask spice. A worth.",180,2,2,2,2,ctx);
            insertDrinkData("Craigganmore","The Cragganmore entry in Diageo's Distillers Edition series is one of the more unusual – it is finished in port pipes for extra sweetness and fruity depth. This is the 2005 vintage, bottled in 2017.",280,3,1,2,3,ctx);
            insertDrinkData("Dailuaine","A tropical-tinger, golden dram from Dailuaine, distilled in 1973 and bottled at an impressive 46 years old by Hunter Laing from the company's oldest stocks of whisky for the Old & Rare range. While Dailuaine is more often for its weighty character, this whisky shows off the fruitier and more tropical side of the distillery, with fresh fruit carefully balanced against riper and richer fruit, and oaky spice. Complex and layered with flavour, it never loses its well-aged elegance.",20,2,2,2,2,ctx);
            insertDrinkData("Dalmore","A favourite expression in the Dalmore stable, this 15-year-old is elegant and smooth, with lipsmacking texture and the flawless balance one would expect from blending maestro Richard Paterson.",2,2,2,2,2,ctx);
            insertDrinkData("Dalwhinnie","A favourite expression in the Dalmore stable, this 15-year-old is elegant and smooth, with lipsmacking texture and the flawless balance one would expect from blending maestro Richard Paterson.",20,2,1,2,1,ctx);
            insertDrinkData("Deanston","Made at Deanston distillery in 1999, this Highland single malt has been matured in a single ex-bourbon cask for 19 years, before being bottled in 2019 by independent bottler Asta Morris.",20,2,1,3,1,ctx);
            insertDrinkData("Dufftown","A gentle but complex whisky from Dufftown in the heart of Speyside – maybe the most important town in Scottish distilling. This 1975-vintage whisky was bottled at 44 years old by Hunter Laing for the Old & Rare range, and the years have allowed the spirit to become mellow and easy-drinking with loads of character. Orchard and citrus fruit are balanced by soft sweetness and lingering oaky spice – a dram to savour.",30,3,0,2,3,ctx);
            insertDrinkData("Edradour","Presented in an elegant Ibisco decanter, this 2005 Edradour 13 Year Old is a single-cask whisky that has been matured for 13 years in oloroso sherry casks and bottled at cask strength. It has rich, sweet and fruity notes on the palate alongside gentle, honeyed cinnamon spice.",30,3,1,2,3,ctx);
            insertDrinkData("GlenDeveronMacduff","A 1980s distillery bottling of Glen Deveron with a screw cap, produced for the Italian market.",30,3,1,2,3,ctx);
            insertDrinkData("GlenElgin","Glen Elgin 12yo is a top-quality malt, highly sought-after for blends. This is a little-seen single malt expression that represents great value for money.",30,3,1,1,3,ctx);
            insertDrinkData("GlenGarioch","Introduced to Glen Garioch's standard range in late 2010, this 12yo is a mix of ex-bourbon and sherry casks, and like the other distillery bottlings since the revamp, has been bottled at a feisty 48%. A real charmer.",10,1,3,2,2,ctx);
            insertDrinkData("GlenGrant","Very reliable quality Speyside from a distillery founded in 1840. Glen Grant is one of the world's best-selling single malts and is particularly popular in Italy. A huge 95 points from Jim Murray's Whisky Bible 2013, along with the title of Single Malt of the Year (10yo & Under).",20,2,0,1,1,ctx);
            insertDrinkData("GlenKeith","The first official bottling from the re-opened Glen Keith distillery in Speyside, which was revived in 2013 and whose spirits normally appear in blends such as Chivas Regal and 100 Pipers. This new, no-age-statement whisky is a gentle dram with notes of poached pears, honeycomb and vanilla toffee.",30,3,2,1,4,ctx);
            insertDrinkData("GlenMoray","A 2006 Glen Moray single malt from the distillery's Cask Project series, matured in ex-Madeira casks for 13 years, before being bottled. Aromas of baked apple, pear, honey and soft spices fill the nose, complemented by notes of sultana, baking spices, brown sugar and cinnamon-baked apples.",20,2,2,2,2,ctx);
            insertDrinkData("GlenOrd","A cask-strength Singleton of Glen Ord, matured in freshly-charred American oak hogsheads for 18 years, before being bottled as part of Diageo's 2019 Special Releases. Delicate aromas of ripe apricots, raspberry jam, candied peel and cinnamon rolls mingle with chocolate-covered cherries, peach, sponge cake and barley sugar on the nose. The palate offers notes of apricot frangipane, baking spices, orange peel, dark chocolate, mint and oak spice.",20,2,2,2,2,ctx);
            insertDrinkData("GlenScotia","Glen Scotia Double Cask starts its life in first-fill bourbon barrels, before being finished in Pedro-Ximenez-sherry casks. Notes of spicy fruit and vanilla.",20,2,0,2,1,ctx);
            insertDrinkData("GlenSpey","The only official bottling from Glen Spey, with most of the whisky going into J&B blends.",30,3,1,2,3,ctx);
            insertDrinkData("Glenallachie","A miniature of 15-year-old Glenallachie single malt, matured in a combination of Pedro Ximenez and oloroso sherry casks, creating a smooth, rich character. Aromas of raisins, butterscotch and sweet spices fill the nose, and the palate offers notes of dark chocolate, orange peel, banana, raisins and caramel.",30,3,1,2,3,ctx);
            insertDrinkData("Glendronach","A dense, heavily-sherried dram from a distillery now producing again after a six-year layoff. A malt best suited to after-dinner sipping.",20,2,1,2,1,ctx);
            insertDrinkData("Glendullan","The ancestral home of House Tully, Riverrun, sits at the junction of the Red Fork of the Trident and the Tumblestone River. The ruling Lord of the Riverlands, House Tully embodies the power and ferocity of the rivers it sits between. Using the power found in their positioning, House Tully forged strong alliances and built a history of honour, family and duty. Built along the banks of the River Fiddich, Glendullan Distillery originally relied on the waters it lay beside, using a water wheel to power the entire distillery. Like the Tullys themselves, this single malt can benefit from a splash of water, opening up flavours of green apple, honey and sweet vanilla.",20,2,1,2,1,ctx);
            insertDrinkData("Glenfarclas","Glenfarclas 10yo is a straw-gold , delicately light, sweet and malty dram leaving a long slightly spicy finish. Always impeccably well-made, this is a whisky that always delivers in quality.",40,4,2,3,4,ctx);
            insertDrinkData("Glenfiddich","A small 20cl bottle of Glenfiddich's flagship 12 year old whisky - as of 2011 the largest selling single malt whisky in the world.",30,3,0,2,3,ctx);
            insertDrinkData("Glengoyne", "Batch 007 of Glengoyne's Cask Strength series is a sweet, malty single malt, and is the first in the collection to be matured in ex-bourbon barrels as well as ex-sherry casks. The palate offers notes of cinnamon, orange, peach and honey.", 240, 2, 1, 2, 1, ctx);
            insertDrinkData("Glenkinchie", "Replacing the 10 year-old as the main expression of the Lowland style in the Classic Malts range, this Glenkinchie 12yo is a bit fuller and more complex.", 190, 2, 2, 2, 2, ctx);
            insertDrinkData("Glenlivet", "The opening salvo of the Glenlivet range, named in honour of the distillery's founder, George Smith. It's classic Glenlivet, with a creamy and fruity character bolstered by the use of first-fill American oak to mature some of the whisky.", 50, 3, 2, 2, 4, ctx);
            insertDrinkData("Glenlossie", "A well-balanced whisky from Glenlossie, one of Speyside's hidden gems. This 1975-vintage dram was matured for 44 years before being bottled by Hunter Lang for the Old & Rare range. It's a fruity whisky, with tropical and ripe orchard fruit notes running through from the nose into the palate, before picking up a touch of nuttiness in the finish.", 30, 2, 2, 2, 2, ctx);
            insertDrinkData("Glenmorangie", "The 'original' sets a high standard for Highland whisky, and has gone from strength to strength since its slightly controversial packaging redesign a few years ago. Medium-bodied and gently warming, with pleasant spicy notes.", 250, 2, 2, 1, 2, ctx);
            insertDrinkData("Glenrothes", "A great whisky for those looking to try Glenrothes for the first time. This sweet, zesty dram has a yellow label that denotes the colour of the grape at the point it is plucked from the vine.", 180, 3, 1, 2, 3, ctx);
            insertDrinkData("Glenturret", "The replacement for the old 12yo as the distillery's standard bottling, Glenturret 10yo has a deliciously rich, fruity character. You can see why Famous Grouse is so successful with Glenturret as its backbone.", 180, 3, 2, 2, 4, ctx);
            insertDrinkData("Highland Park", "Highland Park 12 Year Old remains one of the gold- standard malts for other distillery bottlings to aspire to. With a delicious sweetness (heather-honey is their preferred description) and a warming, silky mouthfeel, this is a whisky that never lets you down. The greatest all-rounder in the world of malt whisky", 120, 2, 1, 2, 1, ctx);
            insertDrinkData("Inchgower", "The first entry in Diageo's Special Releases for northern Speysider Inchgower, part of the 2018 edition of the range. Aged for 8 years in refill American-oak hogsheads, this is a great example of the distillery's fruity and salty spirit – it's by the coast and the distillers swear the sea gives the spirit its salty tang.", 80, 3, 2, 2, 4, ctx);
            insertDrinkData("Isle of Jura", "Elixir is a 10-year-old whisky from Jura distillery. Finished in a combination of American white oak and European sherry casks, this is complex with notes of pineapple, toffee and spice.", 50, 1, 1, 1, 1, ctx);
            insertDrinkData("Knochando", "Formerly carrying a vintage, Knockando's ongoing bourbon-cask bottling has now been released as a 12 year old. Light and fruity with spicy hints.", 40, 3, 2, 1, 4, ctx);
            insertDrinkData("Lagavulin", "9pooiil A 20cl bottle of Lagavulin 16 year old. Arguably the most pungent in terms of pure peat of all the Islay whiskies, it is rich and complex.", 300, 1, 1, 1, 1, ctx);
            insertDrinkData("Laphroig", "A smoky, complex bourbon barrel matured whisky from Laphroaig, this is the 12th edition of the Islay distillery's ever-popular Cask Strength 10 Year Old single malt. Bottled in February 2020, aromas of sweet biscuits, smoked cedar and Manuka honey mingle with pipe tobacco, old leather, candle wax and hints on vanilla on the nose. The palate offers notes of burnt oak, barbecued marshmallow, toffee, sea salt and Belgian caramel waffles, as well as roasted coffee beans, sticky toffee pudding and white pepper, wrapped in waves of peat smoke.", 260, 2, 0, 1, 1, ctx);
            insertDrinkData("Linkwood", "A 1980s bottle of Linkwood 12yo with a decorative woodcut illustration of a castle on the label.", 40, 3, 1, 1, 3, ctx);
            insertDrinkData("Loch Lomond", "A 12-year-old single malt from Loch Lomond, accompanied by a pair of miniature bottles of two other 12 year old whiskies made at the distllery - Inchmurrin and Inchmoan.", 240, 1, 1, 2, 1, ctx);
            insertDrinkData("Longmorn", "A bottling of Longmorn from Connoisseurs Choice featuring their old skool black label. Distilled in 1955, when the distillery was still an independent with its own floor maltings, and bottled 24 years later.", 90, 2, 1, 3, 1, ctx);
            insertDrinkData("Macallan", "An incredible collectors bottling from Macallan, the third in a range of royal bottlings, with only 1953 sets produced. This set celebrates Queen Elizabeth II's coronation in 1953, 60 years before the release, and comprises two sherry cask matured whiskies - one from American oak, one from Spanish. This gives a pair of very different looking whiskies, one golden and one deep mahogany, each labelled with a different iconic image of The Queen: The American oak with Cecil Beaton's famed coronation portrait and the Spanish oak with a 2004 image by Julian Calder.", 90, 3, 1, 2, 3, ctx);
            insertDrinkData("Mannochmore", "Made at Mannochmore distillery in 2008, this Speyside single malt has been matured in a single cask for 10 years, before being bottled in December 2018 for indie bottler Mossburn's Vintage Casks series.", 180, 1, 1, 1, 1, ctx);
            insertDrinkData("Miltonduff", "A 1994 Miltonduff single malt from Douglas Laing's Xtra Old Particular range, matured in a single refill hogshead for a quarter of a century, before being bottled in 2019.", 230, 4, 0, 1, 3, ctx);
            insertDrinkData("Mortlach", "The opening entry in the super-premium Mortlach range, combining bourbon- and sherry-matured whisky to create a spicy, rich and flavoursome dram that shows off the distillery's excellent spirit.-000000000990--0", 90, 2, 3, 1, 2, ctx);
            insertDrinkData("Oban","The brothers of the Night’s Watch take an oath and pledge their lives to protect the Seven Kingdoms and guard them from the terrors that lie beyond the ancient barrier of ice, The Wall, from their base at Castle Black. There lies a frozen wasteland which, legend has it, is inhabited by giants, wildlings and the Night King and his army of White Walkers who bring with them the long night. Just like Castle Black, Oban Distillery sits at the foot of a steep cliff that overlooks the bay, the frontier between the West Highlands and the Islands of Scotland. The richness of the whisky is balanced with a woody, spicy dryness that would keep even the Night’s Watch warm in winter.",120,2,2,2,2,ctx);
            insertDrinkData("OldFettercairn","Old Fettercairn was established in 1824,having been only the second distillery to become legal after the Excise Act of the previous year. Founder Sir Alexander Ramsey ran into financial difficulty and was forced to sell the distillery and the Fasque estate on which it was located in 1830 to a merchant named John Gladstone, who ran both until his death in 1851.",110,2,2,3,2,ctx);
            insertDrinkData("OldPulteney","An intriguing Old Pulteney that's part of the revamped range for 2018. This non-age-statement single malt has been finished in barrels that previously held peated whisky, giving it mellow smoke notes alongside the classic Pulteney character.",250,1,1,2,1,ctx);
            insertDrinkData("RoyalBrackla","Royal Brackla was the first distillery to be allowed to use the 'Royal' title, and 2015 saw it get its own range of single malts. Part of the Last Great Malts series, the 16 Year Old is finished in first-fill oloroso sherry casks and is sweet and spicy, with a hint of smoke in the mix, too.",180,3,2,2,4,ctx);
            insertDrinkData("RoyalLochnagar","A favourite of Queen Victoria and Prince Albert, being conveniently situated close by Balmoral Castle. Royal Lochnagar was originally a key ingredient in VAT 69, but most production is now for single malts.",250,2,2,2,2,ctx);
            insertDrinkData("Scapa","Scapa Skiren is a 2015 release from the Orkney distillery. Aged in first-fill American oak casks, this is creamy and sweet with notes of tropical fruit and heather.",230,2,1,2,1,ctx);
            insertDrinkData("SpeyBurn","A celebratory release from Speyburn to commemorate the 18th anniversary of Bobby Anderson’s position as distillery manager. This single malt, aged for 18 years in American and Spanish oak casks, has aromas of toffee and sugared almonds, as well as tropical fruits on the nose. The palate holds flavours of rich dark chocolate, toffee and a touch of citrus, with plenty of smoke throughout. The finish is long and smoky, with a sweeter honeyed edge.",120,4,1,2,3,ctx);
            insertDrinkData("SpeySide","A very special Glenfarclas 1958, bottled by Signatory at forty years old in 1998 as the jewel in the crown for their tenth anniversary celebrations. Less than 500 decanters were produced from a single sherry butt. An incredible 95.59 points in Whiskybase.",140,2,0,2,1,ctx);
            insertDrinkData("SpringBank","A very special release from Springbank distillery. At 50 years old, this whisky is amongst the oldest releases from the distillery. Origially sold as part of the Millennium set, which included whiskies at 25, 30, 35, 40, 45, and 50 years old.",260,2,2,1,2,ctx);
            insertDrinkData("Strathisla","An independent bottling of 8 year old Strathisla by Gordon & Macphail. We think this was bottled in the 1980s.",70,2,2,3,2,ctx);
            insertDrinkData("Strathmill","This 25 year old Strathmill – the first appearance of the distillery in the Special Releases range – is the lightest in style of this year's line-up. Aged in refill American oak, it's sweet and creamy with the distillery's trademark grassiness.",100,3,2,1,4,ctx);
            insertDrinkData("Talisker","An easy-drinking Talisker, taking the distillery's classically rugged character and softening it out, while maintaining the smoky and sweet notes the distillery is known for. More approachable, but still definitely Talisker.",230,2,3,2,2,ctx);
            insertDrinkData("Tamdhu","Distilled on 2 November 1963 and bottled after more than 50 years in a first-fill sherry butt, this is the oldest ever release from Tamdhu. With just 100 bottles made, this is a rare chance to own a piece of history.",250,2,0,2,1,ctx);
            insertDrinkData("Tamvanulin","A very old distillery bottling of sherried Tamnavulin 20yo, produced for the Italian market at some stage in the 1980s, when they were still using the '-Glenlivet' suffix.",160,3,2,1,4,ctx);
            insertDrinkData("Teaninich","Released during the distillery’s 200th year, this Highland single malt was distilled before Teaninich underwent significant expansion. Reflecting its older style of production, the whisky makes an excellent aperitif or a partner to Asian cuisine. Initially sweet and zesty, this dram layers herbal notes under its toffee, lemon and apple flavours.",70,2,2,0,2,ctx);
            insertDrinkData("Tobermory","Matured exclusively in ex-bourbon casks, Tobermory 12 Year. Launched in 2019, Tobermory 12 Year Old takes centre-stage in the new core range from the distillery. After a two year hiatus as the distillery underwent extensive refurbishments, Tobermory has introduced new bottlings, inspired by the colours of the Isle of Mull. Old has a vibrant, fruity and spicy character with a subtle, salty edge. This Hebridean single malt has sweet, honeyed aromas of vanilla, ripe oranges and delicate flowers. The palate offers notes of fresh citrus, apples and tropical fruits, followed by creamy toffee, floral honey and a subtly, salty undercurrent.",230,1,0,2,1,ctx);
            insertDrinkData("Tomatin","A limited-edition single malt from Tomatin, matured in a collection of 21 casks laid down over the last five decades, including casks that previously held Scotch whisky, bourbon and sherry, as well as re-charred French oak casks. Aromas of sweet pastry, wood shavings, cooked fruit and floral coconut fill the nose. The palate offers notes of Christmas cask, male syrup, red berries, tropical fruits and wax.",140,3,2,2,4,ctx);
            insertDrinkData("Tomintoul","This 21-year-old Tomintoul boasts a floral, fruity, spicy character. This will gladden the hearts of fans of mature easy-drinking Speyside whiskies that don't cost the earth.",190,3,2,2,4,ctx);
            insertDrinkData("Tormore","A darkly-flavoured, sherry-matured whisky from Tormore bottled by Hunter Laing for the Old & Rare range. This was distilled in 1988 and matured for 31 years in a single oloroso sherry butt, giving it a rich character run through with spice, nuts and dark chocolate, as well as more fragrant notes of tobacco and fruit.",230,2,0,1,1,ctx);
            insertDrinkData("Tullibardine","A very rare old 1980s distillery bottling of 5 year old Tullibardine. We estimate this bottle dates from the 1980s.",220,3,2,2,4,ctx);

            isDataInserted = true;
        }
    }

    //    public static int isMusicEmpty(Context ctx){
//        DBHelper helper = new DBHelper(ctx);
//        SQLiteDatabase db = helper.getReadableDatabase();
////        initializeMusic();
////        fetchMusic(this);
//        String sql = "SELECT count(*) FROM " + DATABASE_NAME_DRINK;
////        int hasil = db.execSQL(sql);
//    }
    public static void loadFavDrinkData(Context ctx) {
        DBHelper helper = new DBHelper(ctx);
        SQLiteDatabase db = helper.getReadableDatabase();

        String sql = "SELECT * FROM " + DATABASE_NAME_DRINK + " WHERE ClusterID = '"+LoginActivity.login_user.getClusterID()+"'";
        Cursor c = db.rawQuery(sql, null);
        vFavDrinks.clear();
        while (c.moveToNext()) {

            Drink temp = new Drink();

            temp.setDrinkID(c.getInt(c.getColumnIndex("DrinkID")));
            temp.setDrinkName(c.getString(c.getColumnIndex("DrinkName")));
            temp.setDrinkDescription(c.getString(c.getColumnIndex("DrinkDescription")));
            temp.setDrinkPrice(c.getInt(c.getColumnIndex("DrinkPrice")));
            temp.setDrinkSweetness(c.getInt(c.getColumnIndex("DrinkSweetness")));
            temp.setDrinkSpicy(c.getInt(c.getColumnIndex("DrinkSpicy")));
            temp.setDrinkMalty(c.getInt(c.getColumnIndex("DrinkMalty")));
            temp.setClusterID(c.getInt(c.getColumnIndex("ClusterID")));

            vFavDrinks.add(temp);
        }
//        if(!vDrinks.isEmpty()){
//            isDataInserted = true;
//        }

        c.close();
    }

    public static void loadDrinkData(Context ctx) {
        DBHelper helper = new DBHelper(ctx);
        SQLiteDatabase db = helper.getReadableDatabase();

        String sql = "SELECT * FROM " + DATABASE_NAME_DRINK;
        Cursor c = db.rawQuery(sql, null);
        vDrinks.clear();
        while (c.moveToNext()) {

            Drink temp = new Drink();

            temp.setDrinkID(c.getInt(c.getColumnIndex("DrinkID")));
            temp.setDrinkName(c.getString(c.getColumnIndex("DrinkName")));
            temp.setDrinkDescription(c.getString(c.getColumnIndex("DrinkDescription")));
            temp.setDrinkPrice(c.getInt(c.getColumnIndex("DrinkPrice")));
            temp.setDrinkSweetness(c.getInt(c.getColumnIndex("DrinkSweetness")));
            temp.setDrinkSpicy(c.getInt(c.getColumnIndex("DrinkSpicy")));
            temp.setDrinkMalty(c.getInt(c.getColumnIndex("DrinkMalty")));
            temp.setClusterID(c.getInt(c.getColumnIndex("ClusterID")));

            vDrinks.add(temp);
        }
//        if(!vDrinks.isEmpty()){
//            isDataInserted = true;
//        }

        c.close();
    }


    public static void insertUserData(String UserEmail, String UserPassword, String UserGender, String UserPhoneNumber, int ClusterID, Context ctx) {
        DBHelper helper = new DBHelper(ctx);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("UserEmail", UserEmail);
        cv.put("UserPassword", UserPassword);
        cv.put("UserGender", UserGender);
        cv.put("UserPhoneNumber", UserPhoneNumber);
        cv.put("ClusterID", ClusterID);

        db.insert(DATABASE_NAME_USER, null, cv);
        db.close();
    }

    public static void updateUserData(int UserID, String UserEmail, String UserPassword, String UserGender, String UserPhoneNumber, Context ctx) {
        DBHelper helper = new DBHelper(ctx);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("UserEmail", UserEmail);
        cv.put("UserPassword", UserPassword);
        cv.put("UserGender", UserGender);
        cv.put("UserPhoneNumber", UserPhoneNumber);

        String selection = "UserID = ?";
        String[] selectionArgs = {String.valueOf(UserID)};
        db.update(DATABASE_NAME_USER, cv, selection, selectionArgs);
        db.close();
    }

    public static void updateUserCluster(int UserID, int ClusterID, Context ctx) {
        DBHelper helper = new DBHelper(ctx);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("UserEmail", LoginActivity.login_user.getUserEmail());
        cv.put("UserPassword", LoginActivity.login_user.getUserPassword());
        cv.put("UserGender", LoginActivity.login_user.getUserGender());
        cv.put("UserPhoneNumber", LoginActivity.login_user.getUserPhoneNumber());
        cv.put("ClusterID", ClusterID);

        String selection = "UserID = ?";
        String[] selectionArgs = {String.valueOf(UserID)};
        db.update(DATABASE_NAME_USER, cv, selection, selectionArgs);
        db.close();

        LoginActivity.login_user.setClusterID(ClusterID);
    }

    public static void loadUserData(Context ctx) {
        DBHelper helper = new DBHelper(ctx);

        SQLiteDatabase db = helper.getReadableDatabase();

        String sql = "SELECT * FROM " + DATABASE_NAME_USER;

        Cursor c = db.rawQuery(sql, null);
        vUsers.clear();
        while (c.moveToNext()) {

            User temp = new User();

            temp.setUserID(c.getInt(c.getColumnIndex("UserID")));
            temp.setUserEmail(c.getString(c.getColumnIndex("UserEmail")));
            temp.setUserPassword(c.getString(c.getColumnIndex("UserPassword")));
            temp.setUserGender(c.getString(c.getColumnIndex("UserGender")));
            temp.setUserPhoneNumber(c.getString(c.getColumnIndex("UserPhoneNumber")));
            temp.setClusterID(c.getInt(c.getColumnIndex("ClusterID")));

            vUsers.add(temp);
        }
        c.close();
    }

    public static void insertCartData(int UserID, int DrinkID, int SubTotal, Context ctx) {

        DBHelper helper = new DBHelper(ctx);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("UserID", UserID);
        cv.put("DrinkID", DrinkID);
        cv.put("SubTotal", SubTotal);

        db.insert(DATABASE_NAME_CART, null, cv);
        db.close();
    }

    public static void loadCartData(int UserID, Context ctx) {
        DBHelper helper = new DBHelper(ctx);

        SQLiteDatabase db = helper.getReadableDatabase();

        String sql = "SELECT * FROM " + DATABASE_NAME_CART +" WHERE UserID ="+UserID;

        Cursor c = db.rawQuery(sql, null);
        vCarts.clear();
        if(c != null){
            while (c.moveToNext()) {

                Cart temp = new Cart();

                temp.setUserID(c.getInt(c.getColumnIndex("UserID")));
                temp.setCartID(c.getInt(c.getColumnIndex("CartID")));
                temp.setDrinkID(c.getInt(c.getColumnIndex("DrinkID")));
                temp.setSubTotal(c.getInt(c.getColumnIndex("SubTotal")));

                vCarts.add(temp);
            }
        }
        c.close();
    }

    public static void deleteCartData(int UserID, int DrinkID, Context ctx) {

        DBHelper helper = new DBHelper(ctx);
        SQLiteDatabase db = helper.getWritableDatabase();

        db.delete(DATABASE_NAME_CART,"DrinkID='"+DrinkID+"' AND UserID='"+UserID+"'",null);
        db.close();
    }

    public static void deleteCartByUserID(int UserID, Context ctx) {

        DBHelper helper = new DBHelper(ctx);
        SQLiteDatabase db = helper.getWritableDatabase();

        db.delete(DATABASE_NAME_CART," UserID='"+UserID+"'",null);
        db.close();
    }

//    public static void updateTransactionData(String TransactionDate, int UserID, int MusicID, int position, Context ctx)
//    {
//        DBHelper helper = new DBHelper(ctx);
//        SQLiteDatabase db = helper.getWritableDatabase();
//
//        ContentValues cv = new ContentValues();
//        cv.put("TransactionDate", TransactionDate);
//        cv.put("UserID", UserID);
//        cv.put("MusicID", MusicID);
//
//        String selection =  "TransactionID = ?";
//        String[] selectionArgs = { String.valueOf(position+1) };
//        db.update(DATABASE_NAME_TRANSACTION, cv,selection,selectionArgs);
//        db.close();
//    }

    public static void loadTransactionData(Context ctx) {
        DBHelper helper = new DBHelper(ctx);

        SQLiteDatabase db = helper.getReadableDatabase();

        String sql = "SELECT * FROM " + DATABASE_NAME_TRANSACTION +" WHERE UserID ="+ LoginActivity.login_user.getUserID();

        Cursor c = db.rawQuery(sql, null);
        vTransactions.clear();
        while (c.moveToNext()) {

            Transaction temp = new Transaction();

            temp.setTransactionID(c.getInt(c.getColumnIndex("TransactionID")));
            temp.setTransactionDate(c.getString(c.getColumnIndex("TransactionDate")));
            temp.setUserID(c.getInt(c.getColumnIndex("UserID")));
            temp.setTotalPrice(c.getInt(c.getColumnIndex("TotalPrice")));


            vTransactions.add(temp);
        }
        c.close();
    }

    public static void loadLastTransactionData(Context ctx) {
        DBHelper helper = new DBHelper(ctx);

        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT * FROM " + DATABASE_NAME_TRANSACTION +" WHERE UserID ="+ LoginActivity.login_user.getUserID();
//        String sql = "SELECT * FROM " + DATABASE_NAME_TRANSACTION +" WHERE UserID = '"+ LoginActivity.login_user.getUserID()+"'";

        Cursor c = db.rawQuery(sql, null);

        do {
            lastTransaction.setTransactionID(c.getInt(c.getColumnIndex("TransactionID")));
            lastTransaction.setUserID(c.getInt(c.getColumnIndex("UserID")));
            lastTransaction.setTotalPrice(c.getInt(c.getColumnIndex("TotalPrice")));
            lastTransaction.setTransactionDate(c.getString(c.getColumnIndex("TransactionDate")));
        }while(c.moveToNext());

        c.close();
    }

    public static void insertTransactionData(String TransactionDate, int UserID, Context ctx) {

        DBHelper helper = new DBHelper(ctx);
        SQLiteDatabase db = helper.getWritableDatabase();
        int TotalPrice = 0;
        String sql = "SELECT * FROM " + DATABASE_NAME_CART +" WHERE UserID="+UserID;
        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            TotalPrice+= c.getInt(c.getColumnIndex("SubTotal"));
        }
        ContentValues cv = new ContentValues();
        cv.put("TransactionDate", TransactionDate);
        cv.put("UserID", UserID);
        cv.put("TotalPrice", TotalPrice);

        db.insert(DATABASE_NAME_TRANSACTION, null, cv);
        db.close();
    }
}
