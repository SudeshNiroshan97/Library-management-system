package db;

import util.BookTM;
import util.HandleReturnsTM;
import util.SearchTM;
import util.MemberTM;

import java.util.ArrayList;

public class DB {

    public static ArrayList<BookTM> books = new ArrayList<>();
    public static ArrayList<MemberTM> members = new ArrayList<>();
    public static ArrayList<SearchTM> searchTMS = new ArrayList<>();
    public static ArrayList<HandleReturnsTM> handleReturns = new ArrayList<>();

    static {

         books.add(new BookTM("B001","Thor","Nipun",true));
         books.add(new BookTM("B002","Caption Marvel","Leel",false));
         books.add(new BookTM("B003","Iron Man","Kavisha",true));
         books.add(new BookTM("B004","Strange","Chatur",true));

        members.add(new MemberTM("M001","Sahan","Gankanda","01123445"));
        members.add(new MemberTM("M002","Chatur","Homagama","01342345"));
        members.add(new MemberTM("M003","Kavisha","Moratuwa","03525445"));
        members.add(new MemberTM("M004","Navidu","Matara","01656445"));
    }

}
