package com.example.c_book.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.c_book.R;

import java.util.ArrayList;
import java.util.List;

public class BookHelper extends SQLiteOpenHelper {

    List<String> create_list_sql =new ArrayList<String>();
    String sql;

    private static final String DATABASE_NAME = "BookManage.db";
    private static final int DATABASE_VERSION = 43;

    /**
     * Table sách
     */
    public static final String TBL_BOOK = "tblBook";
    public static final String TBL_ID_BOOK = "id";
    public static final String TBL_NAME_BOOK = "name";
    public static final String TBL_AUTHOR = "author";
    public static final String TBL_BOOK_CATEGORY_ID = "categoryId";
    public static final String TBL_IMAGE_PATH = "image_path";
    public static final String TBL_SCRIPT = "script";

    /**
     * Table categoty
     */
    public static final String TBL_CATEGORY = "tblCategory";
    public static final String TBL_ID_CATEGORY = "categoryId";
    public static final String TBL_NAME_CATEGORY = "name";


    /**
     * Book content
     */
    public static final String TBL_BOOK_CONTENT = "btlContent";
    public static final String TBL_BOOK_CONTENT_ID = "contentId";
    public static final String TBL_BOOK_ID = "bookId";
    public static final String TBL_BOOK_CONTENT_CHAP = "chaper";
    public static final String TBL_BOOK_PAGE_CONTENT = "pageContent";

    /**
     * Book Favorite
     */
    public static final String TBL_FAVORITE = "tblFavorite";
    public static final String TBL_ID_BOOK_FAVORITE = "id";
    public static final String TBL_NAME_BOOK_FAVORITE = "name";
    public static final String TBL_AUTHOR_FAVORITE = "author";
    public static final String TBL_BOOK_CATEGORY_ID_FAVORITE = "categoryId";
    public static final String TBL_IMAGE_PATH_FAVORITE = "image_path";
    public static final String TBL_SCRIPT_FAVORITE = "script";
    public static final String TBL_USERNAME_FAVORITE = "username";

    /**
     * Book History
     */
    public static final String TBL_HISTORY = "tblHistory";
    public static final String TBL_ID_BOOK_HISTORY = "id";
    public static final String TBL_NAME_BOOK_HISTORY = "name";
    public static final String TBL_AUTHOR_HISTORY= "author";
    public static final String TBL_BOOK_CATEGORY_ID_HISTORY = "categoryId";
    public static final String TBL_IMAGE_PATH_HISTORY = "image_path";
    public static final String TBL_SCRIPT_HISTORY = "script";
    public static final String TBL_TIME_HISTORY = "timestamp";
    public static final String TBL_USERNAME_HISTORY = "username";

    /**
     * Table User
     */
    public static final String TBL_USER = "tblUser";
    public static final String TBL_USER_ID = "id";
    public static final String TBL_USERNAME = "username";
    public static final String TBL_PASSWORD = "password";

    public BookHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private void create_table_user(){
        sql = "Create table " + TBL_USER + " (" +
                TBL_USER_ID + " INTEGER PRIMARY KEY, " +
                TBL_USERNAME + " TEXT NOT NULL, " +
                TBL_PASSWORD+ " TEXT NOT NULL)";
        create_list_sql.add(sql);

        sql = "INSERT INTO " + TBL_USER + " VALUES (1, 'a1', '123')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_USER + " VALUES (2, 'a2', '1234')";
        create_list_sql.add(sql);
    }

    private void create_table_book(){
        sql = "CREATE TABLE " + TBL_BOOK + " (" +
                TBL_ID_BOOK + " INTEGER PRIMARY KEY, " +
                TBL_NAME_BOOK + " TEXT NOT NULL, " +
                TBL_AUTHOR + " TEXT NOT NULL, " +
                TBL_BOOK_CATEGORY_ID + " INTEGER, " +
                TBL_IMAGE_PATH + " TEXT NOT NULL, " +
                TBL_SCRIPT + " TEXT NOT NULL, " +
                "FOREIGN KEY(" + TBL_BOOK_CATEGORY_ID + ") REFERENCES " + TBL_CATEGORY + "(" + TBL_ID_CATEGORY + "));";
        create_list_sql.add(sql);

        sql = "INSERT INTO " + TBL_BOOK + " VALUES (1, 'Em sẽ đến cùng cơn mưa', 'Ichikawa Takuji', 1, '" + R.drawable.img_book1 + "', 'sadfsadfsadfsadfasdf')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK + " VALUES (2, 'Tết ở làng địa ngục', 'Thảo Trang', 2, '" + R.drawable.img_book2 + "', 'sadfsadfsadfsadfasdf')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK + " VALUES (3, 'Clean Code', 'Robert C.Martin', 3, '" + R.drawable.img_book3 + "', 'sadfsadfsadfsadfasdf')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK + " VALUES (4, 'Tôi thấy hoa vàng trên cỏ xanh', 'Nguyễn Nhật Ánh', 1, '" + R.drawable.img_book4 + "', 'sadfsadfsadfsadfasdf')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK + " VALUES (5, 'Lâu đài bay của pháp sư Howl', 'Diana Wynne Jones', 4, '" + R.drawable.img_book5 + "', 'sadfsadfsadfsadfasdf')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK + " VALUES (6, 'Thiên tài bên trái, kẻ điên bên phải', 'Cao Minh', 5, '" + R.drawable.img_book6 + "', 'sadfsadfsadfsadfasdf')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK + " VALUES (7, 'Có một người từng là tất cả', 'Trí', 1, '" + R.drawable.img_book7 + "', 'sadfsadfsadfsadfasdf')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK + " VALUES (8, 'Thư tình gửi một người', 'Trinh Công Sơn', 1, '" + R.drawable.img_book8 + "', 'sadfsadfsadfsadfasdf')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK + " VALUES (9, 'Tôi là ai là ai...', 'Trinh Công Sơn', 1, '" + R.drawable.img_book9 + "', 'sadfsadfsadfsadfasdf')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK + " VALUES (10, 'Chí phèo', 'Nam Cao', 6, '" + R.drawable.img_book11 + "', 'sadfsadfsadfsadfasdf')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK + " VALUES (11, 'Lão Hạc', 'Nam Cao', 6, '" + R.drawable.img_book10 + "', 'sadfsadfsadfsadfasdf')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK + " VALUES (12, 'Số đỏ', 'Vũ Trọng Phụng', 6, '" + R.drawable.img_book12 + "', 'sadfsadfsadfsadfasdf')";
        create_list_sql.add(sql);
    }

    private void create_table_category(){
        sql = "Create table " + TBL_CATEGORY + " (" +
                TBL_ID_CATEGORY + " INTEGER PRIMARY KEY, " +
                TBL_NAME_CATEGORY + " TEXT NOT NULL)";
        create_list_sql.add(sql);

//        sql = "INSERT INTO " + TBL_CATEGORY + " VALUES (0, 'Mời chọn thể loại!')";
//        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_CATEGORY + " VALUES (1, 'Tình cảm')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_CATEGORY + " VALUES (2, 'Kinh dị')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_CATEGORY + " VALUES (3, 'Code')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_CATEGORY + " VALUES (4, 'Kịch tính')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_CATEGORY + " VALUES (5, 'Tâm lý')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_CATEGORY + " VALUES (6, 'Văn học dân gian Việt Nam')";
        create_list_sql.add(sql);
    }

    public void create_table_content(){
        sql = "Create table " + TBL_BOOK_CONTENT + " (" +
                TBL_BOOK_CONTENT_ID + " INTEGER PRIMARY KEY, " +
                TBL_BOOK_ID + " INTEGER, " +
                TBL_BOOK_CONTENT_CHAP + " INTEGER, "+
                TBL_BOOK_PAGE_CONTENT + " TEXT, " +
                "FOREIGN KEY(" + TBL_BOOK_ID + ") REFERENCES " + TBL_BOOK + "(" + TBL_ID_BOOK + "));";;
        create_list_sql.add(sql);

        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (1, 1, '1', 'Khi Mio qua đời, tôi đã nghĩ thế này.\n" +
                "\n" +
                "Ai đó, người đã tạo ra tinh cầu của chúng ta, phải chăng lúc đó, đang tạo thêm một tinh cầu khác ở đâu đó trong vũ trụ…\n" +
                "\n" +
                "Tinh cầu nơi người ta sẽ tới sau khi qua đời.\n" +
                "\n" +
                "Tinh cầu mang tên Lưu Trữ.\n" +
                "\n" +
                "“Lưu Trứ?” Yuji hỏi.\n" +
                "\n" +
                "Không phải, tinh cầu Lưu Trữ.\n" +
                "\n" +
                "“Lưu Trứ?”\n" +
                "\n" +
                "Lưu Trữ.\n" +
                "\n" +
                "“Lưu,” Yuji ngẫm nghĩ rồi nói tiếp, “Trứ?”\n" +
                "\n" +
                "Thôi được rồi.\n" +
                "\n" +
                "Nơi ấy giống như một thư viện khổng lồ, rất mực yên tĩnh, sạch sẽ và ngăn nắp.\n" +
                "\n" +
                "Đại để là một nơi rộng mênh mông, với hành lang dài ngút mắt chạy xuyên các tòa nhà.\n" +
                "\n" +
                "Tại đây, những người đã rời bỏ tinh cầu của chúng ta đang tận hưởng một cuộc sống an bình.\n" +
                "\n" +
                "Có thể nói tinh cầu này ở trong chính trái tim chúng ta.\n" +
                "\n" +
                "“Nghĩa là sao ạ?”\n" +
                "\n" +
                "Yuji thắc mắc.\n" +
                "\n" +
                "Là thế này, khi mẹ Mio qua đời, các cô các bác đã nói với Yuji thế nào nhỉ? Là mẹ vẫn ở trong trái tim của con đúng không?\n" +
                "\n" +
                "“Vâng.”\n" +
                "\n" +
                "Bởi vậy, tinh cầu này là nơi những người sống ở trong tim của tất cả mọi người trên thế giới cư ngụ cùng nhau.\n" +
                "\n" +
                "Chừng nào vẫn có ai đó nghĩ đến, họ còn được sống ở tinh cầu đó.\n" +
                "\n" +
                "“Nếu ai đó quên họ thì sao ạ?”\n" +
                "\n" +
                "Ừ, thì họ sẽ buộc phải rời tinh cầu.\n" +
                "\n" +
                "Lần này mới là “chia tay” thực sự.\n" +
                "\n" +
                "Vào buổi tối cuối cùng, tất cả bạn bè sẽ tụ tập lại để tổ chức tiệc chia tay.\n" +
                "\n" +
                "“Có ăn bánh ga tô không?”\n" +
                "\n" +
                "Có, có bánh ga tô.\n" +
                "\n" +
                "“Cả trứng cá hồi?”\n" +
                "\n" +
                "Ừ, cả trứng cá hồi. (Trứng cá hồi là món khoái khẩu của Yuji.)\n" +
                "\n" +
                "“Thế còn…”\n" +
                "\n" +
                "Đủ mọi thứ. Con không phải lo.\n" +
                "\n" +
                "“Thế, tinh cầu ấy có Jim Button không?”\n" +
                "\n" +
                "Sao cơ?\n" +
                "\n" +
                "“Vì con biết Jim Button. Tức là Jim Button ở trong trái tim con phải không?”\n" +
                "\n" +
                "Ừ, ừ, (tối qua, tôi đã đọc cho Yuji nghe truyện\n" +
                "\n" +
                "Jim Button và bác lái tàu Luke\n" +
                "\n" +
                "), bố nghĩ là có, có thể lắm.\n" +
                "\n" +
                "“Thế còn đầu máy Emma? Emma cũng ở đấy chứ?”\n" +
                "\n" +
                "Emma không ở đấy.Chỉ có con người mới ở đấy thôi.\n" +
                "\n" +
                "“Hừm.” Yuji nói.\n" +
                "\n" +
                "Có Jim Button, có cả Momo (1).\n" +
                "\n" +
                "Có cô bé quàng khăn đỏ, đương nhiên cả Anne Frank (2) nữa, Hitler và Rudolf Hess (3) chắc cũng có ở đó.\n" +
                "\n" +
                "Có cả Aristotle và Newton.\n" +
                "\n" +
                "“Mọi người làm gì ở đó ạ?”\n" +
                "\n" +
                "Làm gì à, họ cứ lặng lẽ sống thôi.\n" +
                "\n" +
                "“Chỉ thế thôi?”\n" +
                "\n" +
                "Chỉ thế thôi là sao, à, có lẽ họ còn suy nghĩ về điều gì đó chăng?\n" +
                "\n" +
                "“Suy nghĩ? Nghĩ gì thế ạ?”\n" +
                "\n" +
                "Điều gì đó vô cùng phức tạp. Phải suy nghĩ rất lâu mới ra được câu trả lời. Vì thế, ngay cả khi đến tinh cầu Lưu Trữ, họ vẫn tiếp tục suy nghĩ.\n" +
                "\n" +
                "“Cả mẹ cũng thế?”\n" +
                "\n" +
                "Không, mẹ chỉ nghĩ về Yuji.\n" +
                "\n" +
                "“Thật hả?”\n" +
                "\n" +
                "Thật.\n" +
                "\n" +
                "Nên Yuji không được quên mẹ đâu đấy.\n" +
                "\n" +
                "“Con không quên đâu.”\n" +
                "\n" +
                "Nhưng con còn nhỏ quá, Mới ở được với mẹ có năm năm.\n" +
                "\n" +
                "“Vâng.”\n" +
                "\n" +
                "Vậy bố sẽ kể cho con trước kia mẹ là cô gái như thế nào.\n" +
                "\n" +
                "Mẹ đã gặp và kết hôn với bố ra sao.\n" +
                "\n" +
                "Mẹ đã vui mừng thế nào khi Yuji chào đời.\n" +
                "\n" +
                "“Vâng.”\n" +
                "\n" +
                "Bố mong con sẽ luôn nhớ những điều ấy.\n" +
                "\n" +
                "Nhất định con phải nhớ đến mẹ đấy, để khi đến lượt bố tới tinh cầu ấy, bố vẫn có thể gặp được mẹ.\n" +
                "\n" +
                "\n" +
                "Con hiểu chứ?\n" +
                "\n" +
                "“Gì ạ?”\n" +
                "\n" +
                "Thôi được rồi.\n" +
                "\n" +
                "-------------\n" +
                "\n" +
                "Chú thích:\n" +
                "\n" +
                "(1): Tên nhân vật chính trong truyện thiếu nhi cùng tên của nhà văn Michael Ende.\n" +
                "\n" +
                "(2): Cô bé người Đức gốc Do Thái, tác giả cuốn nhật ký nổi tiếng\n" +
                "\n" +
                "Nhật ký Anne Frank\n" +
                "\n" +
                "(3): Cận vệ thân tín của Hitler dưới thời Đức Quốc xã.\n" +
                "\n" +
                "“Con chuẩn bị đi học chưa?”\n" +
                "\n" +
                "“Gì ạ?”\n" +
                "\n" +
                "“Con chuẩn bị xong chưa? Đeo thẻ tên vào chưa?”\n" +
                "\n" +
                "“Gì ạ?”\n" +
                "\n" +
                "Sao thằng bé lại nghễnh ngãng thế nhỉ? Hồi Mio còn sống, nó đâu có thế này. Có vấn đề về tâm lý chăng?\n" +
                "\n" +
                "“Đến giờ rồi. Đi thôi.”\n" +
                "\n" +
                "Tôi cầm tay Yuji, lúc này vẫn còn ngái ngủ, kéo ra khỏi nhà. Tôi trao Yuji cho cậu bé phụ trách dẫn các em đi học (1) đang đợi dưới chân cầu thang, rồi đứng dõi theo thằng bé. Đi cạnh anh phụ trách lớp Sáu, trông Yuji như trẻ mẫu giáo. So với tuổi lên sáu, thằng bé còn nhỏ quá. Dường như nó đã quên mất việc phải lớn lên.\n" +
                "\n" +
                "Nhìn từ đằng sau, gáy của Yuji gầy và trắng như cổ hạc. Phần tóc lộ ra bên dưới chiếc mũ vàng có màu giống nước trà Darjeeling pha sữa.\n" +
                "\n" +
                "Vài năm nữa, mái tóc trông như tóc của hoàng tử Anh quốc này thể nào cũng xoăn tít lại.\n" +
                "\n" +
                "Tôi đã trải qua chặng đường này rồi. Nguyên nhân là bởi một số hoóc môn bị tiết ra quá nhiều vào tuổi dậy thì. Đến khi ấy, Yuji sẽ lớn bổng lên, hơn cả tôi bây giờ. Yuji sẽ gặp một cô gái giống mẹ, sẽ yêu, và nếu thuận lợi sẽ có được một bản sao mang nửa gien di truyền của mình.Từ thời xa xưa, con người đã luôn như vậy (phần lớn mọi sinh vật đều thế), chừng nào tinh cầu này còn quay, quy trình vẫn sẽ được lặp lại.\n" +
                "\n" +
                "Tôi leo lên chiếc xe đạp cũ dựng dưới chân cầu thang, nhấn bàn đạp hướng về văn phòng luật nơi tôi làm việc. Văn phòng cách khu nhà tôi ở chưa tới năm phút đạp xe. Khoảng cách lý tưởng đối với một người không chịu nổi các phương tiện giao thông như tôi.\n" +
                "\n" +
                "Tôi làm ở đây đã được tám năm.\n" +
                "\n" +
                "Khoảng thời gian đó không hề ngắn. Lấy vợ, có con, và vợ rời đến một tinh cầu khác.\n" +
                "\n" +
                "Khoảng thời gian đủ dài cho ngần ấy chuyện xảy ra.\n" +
                "\n" +
                "Vậy đấy, giờ ở tuổi hai chín, tôi đã là ông bố độc thân với một cậu con trai sáu tuổi.\n" +
                "\n" +
                "Giám đốc văn phòng rất tốt với tôi.\n" +
                "\n" +
                "Tám năm trước ông đã là một ông già, giờ đây ông vẫn là một ông già và sẽ còn tiếp tục là một ông già cho đến lúc nhắm mắt. Tôi không hình dung nổi ông giám đốc không phải là một ông già. Chẳng rõ ông đã bao nhiêu tuổi. Chỉ biết là ông đã qua tuổi tám mươi.\n" +
                "\n" +
                "Bộ dạng ông rất giống loài chó St. Bernard có thùng rượu treo ở cổ (2). Có điều, thứ treo ở cổ ông là cái cằm hai ngấn. Ông cũng giống loài chó này ở tính cách điềm đạm, hòa nhã, mắt lúc nào cũng lim dim.\n" +
                "\n" +
                "Giả sử có một con St. Bernard già nua ngồi thế chỗ ông ở góc phòng, chưa chắc tôi đã phát hiện ra.\n" +
                "\n" +
                "Khi Mio mất, tôi vốn yếu đuối lại càng thêm yếu đuối, ngay cả chút sức lực để thở cũng ngày một cạn kiệt.\n" +
                "\n" +
                "Suốt một thời gian dài, tôi bỏ bê công việc, gây biết bao phiền toái cho văn phòng. Tuy vậy, ông giám đốc không tìm người khác thay thế mà chờ cho tới lúc tôi đủ sức gượng dậy. Sau đó, ông còn cho phép tôi chỉ làm đến bốn giờ chiều. Tôi đề đạt nguyện vọng rằng không muốn Yuji ở nhà một mình sau giờ tan học và ông đã đáp ứng. Làm vậy, tuy lương ít đi, nhưng bù lại tôi có được khoảng thời gian không thể đổi thành tiền.\n" +
                "\n" +
                "Nghe nói ở thị trấn khác có nhận giữ trẻ sau giờ học, nhưng nơi tôi ở không tồn tại mô hình hữu ích này.\n" +
                "\n" +
                "Bởi vậy, tôi rất biết ơn ông giám đốc.\n" +
                "\n" +
                "Đến văn phòng, tôi cất tiếng chào Nagase, người có mặt sớm hơn tôi.\n" +
                "\n" +
                "“Chào cô.”\n" +
                "\n" +
                "Cô chào đáp lại.\n" +
                "\n" +
                "“Chào anh.”\n" +
                "\n" +
                "Nagase làm ở đây trước tôi. Theo lời cô, học xong cấp III là cô vào văn phòng này luôn, vậy nên tính ra cô cũng phải hai sáu tuổi rồi.')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (2, 1, '2', 'Đôi khi tôi thấy lo cho cô, không biết liệu có chỗ nào dành cho cô giữa những phụ nữ thời nay chẳng ngại ngần thể hiện bản thân.\n" +
                "\n" +
                "Nhỡ đâu một ngày nào đó, trong lúc chen lấn xô đẩy, cô bị sẩy chân, ngã khỏi rìa Trái Đất thì sao? Tôi đã nghĩ đến tình huống ấy.\n" +
                "\n" +
                "Ông giám đốc vẫn chưa tới văn phòng.\n" +
                "\n" +
                "Gần đây, ông giám đốc bỗng nhiên đi làm muộn hơn hẳn. Dù tôi chẳng thấy điều đó có mối liên hệ nào với tốc độ đi bộ đã giảm sút của ông.\n" +
                "\n" +
                "Bởi vậy, bây giờ và một lát nữa, văn phòng sẽ chỉ có hai người. Đó là toàn bộ số nhân viên ở đây. Xét theo khối lượng công việc thì đây là con số hợp lý.\n" +
                "\n" +
                "Tôi ngồi vào bàn làm việc, lướt qua đống giấy nhớ dán trên bảng ghi chú. Những dòng chữ rất khó đọc, nào là “đến ngân hàng lúc hai giờ”, “đến chỗ khách hàng lấy hồ sơ”, “đến Sở Tư pháp”. Những lời nhắn mà tôi của ngày hôm qua gửi đến tôi của ngày hôm nay.\n" +
                "\n" +
                "Trí nhớ của tôi rất tệ. Thành thử tôi phải thường xuyên ghi lại những việc cần làm.\n" +
                "\n" +
                "Trí nhớ kém chỉ là một trong vô vàn vấn đề về sức khỏe tôi đang phải chịu đựng. Giải thích ngắn gọn thì đó là do sơ đồ thiết kế được chuẩn bị để làm ra tôi có sai sót.\n" +
                "\n" +
                "Một sai sót rất nhỏ.\n" +
                "\n" +
                "Việc dùng bút phủ xóa chỗ sai đi rồi viết bút bi đè lên đã không phát huy tác dụng. Tất nhiên, đây chỉ là cách nói ví von, nhưng tôi đồ rằng, trên thực tế hẳn đã xảy ra việc tương tự.\n" +
                "\n" +
                "Rốt cuộc, không rõ là do người viết cẩu thả hay bởi chữ bên dưới lớp phủ trắng nhòe lên dòng viết bi đè bên trên, nhưng đại để trong não tôi, tình trạng khá hỗn loạn, hậu quả của việc những chất hóa học quan trọng bị tiết ra vô tội vạ. Điều đó khiến tôi trở nên phấn khích quá độ, lo lắng không đúng lúc, không thể quên được những việc muốn quên, nhưng lại quên những việc không được phép quên.\n" +
                "\n" +
                "Đúng là bất tiện kinh khủng. Hoạt động bị hạn chế, lúc nào cũng mệt mỏi. Tôi thường xuyên mắc lỗi trong công việc, bị mọi người đánh giá thấp đến bất công.\n" +
                "\n" +
                "Nói cách khác, người ta coi tôi chẳng khác gì một thằng bất tài vô dụng. Tôi không đi phân trần với từng người rằng đó là tại những chất hóa học trong não tôi. Làm thế rất phiền phức mà chưa chắc mọi người đã thông cảm, vả lại, nều chỉ nhìn vào kết quả thì phải thừa nhận là họ có lý.\n" +
                "\n" +
                "Ông giám đốc là một người độ lượng, tôi như thế nhưng ông chẳng những không đuổi việc mà vẫn tiếp tục sử dụng tôi. Nagase thì chưa từng tỏ thái độ khó chịu và luôn hỗ trợ tôi trong công việc\n" +
                "\n" +
                "Tôi biết ơn hai người đó lắm.\n" +
                "\n" +
                "Hoàn tất một số việc tại văn phòng, tôi nhét tài liệu vào cặp rồi ra ngoài. Tôi đạp xe đến Sở Tư pháp.\n" +
                "\n" +
                "Tôi không có bằng lái ô tô. Hồi năm thứ hai đại học, tôi có thi một lần nhưng không vượt qua nổi vòng thi cấp giấy phép tạm.\n" +
                "\n" +
                "Trước đó vài tháng, lần đầu tiên tôi phát hiện ra não mình có vấn đề. Cạch! Công tắc bật lên, van mở ra, kim áp kế vọt lên mức kịch trần. Khi chuẩn bị thi lấy bằng lái, tôi vẫn đang trong tình trạng hỗn loạn. Có lẽ tôi gắng gượng được đến tận kỳ thi cấp giấy phép tạm thời cũng đã là đáng hoan nghênh lắm rồi.\n" +
                "\n" +
                "Hôm đi thi, đang ngồi sau tay lái với thầy hướng dẫn thì những chất hóa học kia bắt đầu túa ra ào ào trong huyết mạch. Tôi cảm thấy lo lắng thái quá, không duy trì nổi sự tập trung cần thiết. Nỗi lo cứ lớn dần lên với tốc độ kinh hoàng, hệt như những quân cờ domino đang theo nhau đổ ập xuống hàng loạt.\n" +
                "\n" +
                "Sự kinh hoàng ấy thực sự là rất kinh hoàng, có thể biểu thị theo hàm số mũ.\n" +
                "\n" +
                "Mình sắp chết.\n" +
                "\n" +
                "Tôi đã nghĩ “mình sắp chết” thật.\n" +
                "\n" +
                "Hồi đó, tôi đã nghĩ mình sẽ chết đến vài chục lần mỗi ngày (đến tận bây giờ, có hôm tôi vẫn nghĩ thế đến vài lần).\n" +
                "\n" +
                "Tôi bỏ dở bài sát hạch lái xe hôm đó. Tôi còn cố thêm hai lần nữa trước khi bỏ hẳn ý định lấy bằng.\n" +
                "\n" +
                "Buổi trưa, tôi ngồi trên ghế đá trong công viên, ăn cơm hộp tự làm. Tôi đang cắt giảm tối đa những gì có thể trong tình cảnh chật vật này.\n" +
                "\n" +
                "Với lại, cơm hộp ở cửa hàng tiện dụng dễ khiến tôi đau bụng. Người khác có thể chẳng hề gì, nhưng đối với tôi, các chất phụ gia có thể đe dọa đến tính mạng.\n" +
                "\n" +
                "Bộ cảm ứng trong cơ thể tôi nhạy hơn người thường đến vài chục lần. Tôi vô cùng mẫn cảm với sự biến đổi của nhiệt độ, độ ẩm và áp suất. Vì vậy mà tôi luôn phải đeo đồng hồ có cảm biến áp suất, giúp tôi biết trước thay đổi sắp xảy ra để kịp ứng phó.\n" +
                "\n" +
                "Bão là thứ rất đáng sợ.\n" +
                "\n" +
                "Tôi rất phục những người bình thường ở sự dẻo dai của họ. Đôi lúc tôi nghĩ mình giống một loài động vật ăn cỏ quá yếu ớt nên sắp bị tuyệt chủng.\n" +
                "\n" +
                "Không chừng tên tôi có trong Sách Đỏ cũng nên.\n" +
                "\n" +
                "Buổi chiều, sau khi gặp vài khách hàng, tôi quay về văn phòng.\n" +
                "\n" +
                "Đi ra ngoài tôi vẫn phải mang theo giấy nhớ. Tôi đánh dấu X vào cạnh tên những khách hàng đã gặp để biết chắc những khách hàng còn lại là ai. Nếu không làm vậy, tôi sẽ đến gặp cùng một khách hàng hai lần hoặc bỏ qua những khách hàng cần gặp mà đi thẳng về văn phòng.Tôi trao cho Nagase hồ sơ vừa lấy từ khách hàng rồi làm nốt mấy việc ở văn phòng, cũng vừa lúc hết giờ làm. Chưa thấy bong dáng ông giám đốc đâu cả.\n" +
                "\n" +
                "Tôi chào tạm biệt Nagase.\n" +
                "\n" +
                "Bỗng Nagase gọi tôi lại: “Anh này…”\n" +
                "\n" +
                "“Gì hả cô?”\n" +
                "\n" +
                "Thấy tôi hỏi, cô tỏ ra bối rối, kéo đi kéo lại vài lần cổ và tay áo sơ mi.\n" +
                "\n" +
                "“Không ạ.” Nagase nói. “Không có gì đâu ạ.”\n" +
                "\n" +
                "“Vậy à.”\n" +
                "\n" +
                "Tôi nghĩ một giây rồi mỉm cười.\n" +
                "\n" +
                "“Chào cô nhé.”\n" +
                "\n" +
                "“Chào anh.”\n" +
                "\n" +
                "Tôi đạp xe về nhà, Yuji đang nằm đọc sách. Ngó qua bìa tôi thấy đó là cuốn\n" +
                "\n" +
                "Momo\n" +
                "\n" +
                "của Michael Ende.\n" +
                "\n" +
                "“Con đọc được hả?” tôi hỏi.\n" +
                "\n" +
                "Yuji liền quay sang nhìn tôi: “Gì ạ?”\n" +
                "\n" +
                "Tôi hỏi lại lần nữa: “Con đọc được cuốn đó à?”\n" +
                "\n" +
                "“Dạ.” Yuji trả lời. “Một ít thôi ạ.”\n" +
                "\n" +
                "“Đi mua thức ăn cho bữa tối nào.”\n" +
                "\n" +
                "Tôi thay quần áo, mặc áo nỉ chui đầu, quần bò, rồi gọi Yuji.\n" +
                "\n" +
                "“Tối nay con muốn ăn gì?”\n" +
                "\n" +
                "“Cơm cà ri.”\n" +
                "\n" +
                "Hai bố con mở cửa bước ra ngoài. Lúc đi xuống cầu thang, tôi bảo:\n" +
                "\n" +
                "“Hôm kia mình ăn cơm cà ri rồi.”\n" +
                "\n" +
                "“Nhưng con vẫn muốn ăn.”\n" +
                "\n" +
                "“Hình như Chủ nhật vừa rồi cũng ăn cà ri.”\n" +
                "\n" +
                "“Vâng, nhưng con vẫn muốn ăn.”\n" +
                "\n" +
                "“Nấu cà ri lâu lắm.”\n" +
                "\n" +
                "“Không sao ạ.”\n" +
                "\n" +
                "“Được rồi.”\n" +
                "\n" +
                "---------\n" +
                "\n" +
                "\n" +
                "Chú thích:\n" +
                "\n" +
                "(1): Ở Nhật, nhiều trường học thường phân công học sinh lớp lớn tới tận nhà các em lớp nhỏ cùng khu phố và dẫn các em đi học. Việc này một phần là để đảm bảo an toàn cho các học sinh lớp nhỏ, một phần để tăng thêm tính hòa đồng giữa cộng đồng thiếu nhi.\n" +
                "\n" +
                "(2): Ở phương Tây, người ta thường đeo lên cổ giống chó St. Bernard những thùng rượu mini bằng gỗ (còn gọi là \"keg collar\") thay vì vòng cổ thông thường như những giống chó khác.\n" +
                "\n" +
                "Chúng tôi mua bột cà ri, hành tây, cà rốt và khoai tây ở trung tâm mua sắm trước cửa ga. Tay trái tôi xách túi ni long đựng đồ, tay phải dắt Yuji. Tay thằng bé lúc nào cũng nhớp nháp mồ hôi.\n" +
                "\n" +
                "Vốn hay lo lắng thái quá nên khi đi bộ ngoài đường, tôi không bao giờ rời tay Yuji. Tôi nói với thằng bé:\n" +
                "\n" +
                "“Ô tô đáng sợ lắm. Phải thật cẩn thận.”\n" +
                "\n" +
                "“Dạ.”\n" +
                "\n" +
                "“Mỗi ngày có hàng chục người chết vì tai nạn ô tô đấy.”\n" +
                "\n" +
                "“Thật ạ?”\n" +
                "\n" +
                "“Đúng thế. Nếu ngày nào cũng có ngần ấy người chết vì tàu điện, máy bay, người ta sẽ cho rằng chúng bị lỗi ở bộ phận quan trọng và loại bỏ những phương tiện ấy.”\n" +
                "\n" +
                "“Thế người ta sẽ loại bỏ ô tô ạ?”\n" +
                "\n" +
                "“Không hề. Lượng ô tô đang tăng lên.”“Vì sao?”\n" +
                "\n" +
                "“Chẳng biết nữa.”\n" +
                "\n" +
                "“Lạ nhỉ!”\n" +
                "\n" +
                "Rất là lạ.\n" +
                "\n" +
                "Trên đường về, chúng tôi tạt vào công viên số 17. (Không biết có tất cả bao nhiêu công viên ở thị trấn này? Có lần tôi đã nhìn thấy công viên số 21.)\n" +
                "\n" +
                "Trong công viên, như thường lệ, đã có mặt thầy Nombre và con chó Pooh.\n" +
                "\n" +
                "Tôi không biết tên thật của thầy Nombre. Nghe nói hồi trẻ, lúc còn dạy ở trường tiểu học người ta đã gọi ông như vậy. Lần đầu tiên nghe thấy tên này, tôi đã hỏi ông.\n" +
                "\n" +
                "“Nombre có phải cách gọi các số đánh bên dưới mỗi trang tiểu thuyết không ạ?”\n" +
                "\n" +
                "“Đúng rồi!” ông trả lời.\n" +
                "\n" +
                "Người ông lúc nào cũng run lẩy bẩy. Cứ như chú chó nhỏ bị ngấm nước mưa. Có lẽ tại ông đã quá già.\n" +
                "\n" +
                "“Sao từ đó lại thành biệt danh của thầy?”\n" +
                "\n" +
                "Ông khẽ lắc đầu. Hoặc có thể chỉ là ông đang run lẩy bẩy thôi.\n" +
                "\n" +
                "“Tại sao nhỉ? Hoặc giả những người xung quanh cho rằng đời tôi hoàn toàn chẳng có gì chăng? Giống như quyển sách giở mãi toàn thấy giấy trắng, trang nào cũng chỉ có mỗi số trang.”\n" +
                "\n" +
                "“Thật ạ?” tôi hỏi.\n" +
                "\n" +
                "Ông nhìn vào không trung bằng đôi mắt đục ngầu đặc trưng của người già.\n" +
                "\n" +
                "“Đời tôi, toàn bộ chỉ dành cho em gái mình.”\n" +
                "\n" +
                "Con Pooh lông xù, ngồi dưới chân ông há miệng ngáp dài.\n" +
                "\n" +
                "(Con chó này có “tên thật” hẳn hoi, nhưng Yuji tự đặt tên cho nó là Pooh.)\n" +
                "\n" +
                "Tôi và em gái chênh nhau mười ba tuổi. Giữa tôi và em gái còn một đứa em trai nữa, nhưng sau khi bố mẹ lần lượt qua đời, thằng em tôi vội vàng bỏ đi sống tự lập. Nhà chỉ còn mỗi tôi và em gái.\n" +
                "\n" +
                "Em tôi từ nhỏ đã ốm yếu, bác sĩ hồi ấy chẩn đoán nó không thể sống đến năm mười lăm tuổi.\n" +
                "\n" +
                "Chẩn đoán là gì ạ? Yuji ngồi nghe bên cạnh hỏi. Không tìm được cách giải thích nào thấu đáo, tôi đành trả lời: “Con nghĩ thế nào thì nó là như thế.”\n" +
                "\n" +
                "“Biết mà!” Yuji cười.\n" +
                "\n" +
                "Tôi dám chắc thằng bé đang nghĩ đến một thứ hoàn toàn khác.\n" +
                "\n" +
                "Khi em trai tôi bỏ đi, em gái tôi mới mười bốn, còn tôi hai bảy. Tôi xác định sẽ chăm sóc em gái đến giây phút cuối cùng nên đã chọn cuộc sống chỉ có hai anh em. Khi ấy tôi cũng đến tuổi lấy vợ, trong lòng cũng đang thương thầm một cô. Nhưng tôi tự nhủ phải lo cho em trước, chuyện mình thì để sau. Thực tế là việc chữa trị cho em tôi tiêu tốn rất nhiều tiền. Giả sử chuyện tôi với cô gái kia có đơm hoa kết quả đi nữa, cũng chưa chắc tiến được tới hôn nhân.\n" +
                "\n" +
                "Cứ như vậy năm tháng trôi qua với tốc độ kinh ngạc.\n" +
                "\n" +
                "Nhanh quá cậu ạ! Hay chỉ riêng với tôi mới nhanh đặc biệt như vậy. Thậm chí tôi còn ngờ rằng kẻ nào đó cao tay đã đánh cấp mất thời gian của tôi.')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (3, 1, '3', 'Content chap 3...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (4, 1, '4', 'Content chap 4...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (5, 2, '1', 'Content chap 1...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (6, 2, '2', 'Content chap 2...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (7, 2, '3', 'Content chap 3...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (8, 3, '1', 'Content chap 1...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (9, 3, '2', 'Content chap 2...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (10, 4, '1', 'Content chap 1...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (11, 4, '2', 'Content chap 2...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (12, 4, '3', 'Content chap 3...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (13, 4, '4', 'Content chap 4...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (14, 5, '1', 'Content chap 1...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (15, 5, '2', 'Content chap 2...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (16, 6, '1', 'Content chap 1...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (17, 6, '2', 'Content chap 2...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (18, 6, '3', 'Content chap 3...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (19, 6, '4', 'Content chap 4...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (20, 7, '1', 'Content chap 1...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (21, 7, '2', 'Content chap 2...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (22, 7, '3', 'Content chap 3...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (23, 7, '4', 'Content chap 4...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (24, 8, '1', 'Content chap 1...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (25, 8, '2', 'Content chap 2...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (26, 8, '3', 'Content chap 3...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (27, 8, '4', 'Content chap 4...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (28, 9, '1', 'Content chap 1...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (29, 9, '2', 'Content chap 2...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (30, 9, '3', 'Content chap 3...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (31, 9, '4', 'Content chap 4...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (32, 10, '1', 'Content chap 1...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (33, 10, '2', 'Content chap 2...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (34, 10, '3', 'Content chap 3...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (35, 10, '4', 'Content chap 4...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (36, 11, '1', 'Content chap 1...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (37, 11, '2', 'Content chap 2...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (38, 11, '3', 'Content chap 3...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (39, 11, '4', 'Content chap 4...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (40, 12, '1', 'Content chap 1...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (41, 12, '2', 'Content chap 2...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (42, 12, '3', 'Content chap 3...')";
        create_list_sql.add(sql);
        sql = "INSERT INTO " + TBL_BOOK_CONTENT + " VALUES (43, 12, '4', 'Content chap 4...')";
        create_list_sql.add(sql);

    }

    private void create_table_favorite(){
        sql = "CREATE TABLE " + TBL_FAVORITE + " (" +
                TBL_ID_BOOK_FAVORITE + " INTEGER, " +
                TBL_NAME_BOOK_FAVORITE + " TEXT NOT NULL, " +
                TBL_AUTHOR_FAVORITE + " TEXT NOT NULL, " +
                TBL_BOOK_CATEGORY_ID_FAVORITE + " INTEGER, " +
                TBL_IMAGE_PATH_FAVORITE + " TEXT NOT NULL, " +
                TBL_SCRIPT_FAVORITE + " TEXT NOT NULL, " +
                TBL_USERNAME_FAVORITE + " TEXT NOT NULL, " +
                "FOREIGN KEY(" + TBL_BOOK_CATEGORY_ID_FAVORITE + ") REFERENCES " + TBL_CATEGORY + "(" + TBL_ID_CATEGORY + "));";
        create_list_sql.add(sql);

//        sql = "INSERT INTO " + TBL_FAVORITE + " VALUES (1, 'Em sẽ đến cùng cơn mưa', 'Ichikawa Takuji', 1, '" + R.drawable.img_book1 + "', 'sadfsadfsadfsadfasdf', 'a1')";
//        create_list_sql.add(sql);
//        sql = "INSERT INTO " + TBL_FAVORITE + " VALUES (2, 'Tết ở làng địa ngục', 'Thảo Trang', 2, '" + R.drawable.img_book2 + "', 'sadfsadfsadfsadfasdf', 'a2')";
//        create_list_sql.add(sql);
    }

    private void create_table_history(){
        sql = "CREATE TABLE " + TBL_HISTORY + " (" +
                TBL_ID_BOOK_HISTORY + " INTEGER, " +
                TBL_NAME_BOOK_HISTORY + " TEXT NOT NULL, " +
                TBL_AUTHOR_HISTORY + " TEXT NOT NULL, " +
                TBL_BOOK_CATEGORY_ID_HISTORY + " INTEGER, " +
                TBL_IMAGE_PATH_HISTORY + " TEXT NOT NULL, " +
                TBL_SCRIPT_HISTORY + " TEXT NOT NULL, " +
                TBL_TIME_HISTORY + " DATETIME NOT NULL, " +
                TBL_USERNAME_HISTORY + " TEXT NOT NULL, " +
                "FOREIGN KEY(" + TBL_BOOK_CATEGORY_ID_HISTORY + ") REFERENCES " + TBL_CATEGORY + "(" + TBL_ID_CATEGORY + "));";
        create_list_sql.add(sql);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        create_table_book();
        create_table_category();
        create_table_content();
        create_table_favorite();
        create_table_history();
        create_table_user();
        for(int i = 0; i< create_list_sql.size(); i++){
            db.execSQL(create_list_sql.get(i));
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(BookHelper.class.getName(), "Upgrading database from version"
                + oldVersion + " to " + newVersion
                +", which will destroy all old data" );
        db.execSQL(" DROP TABLE IF EXISTS " + TBL_BOOK);
        db.execSQL(" DROP TABLE IF EXISTS " + TBL_CATEGORY);
        db.execSQL(" DROP TABLE IF EXISTS " + TBL_BOOK_CONTENT);
        db.execSQL(" DROP TABLE IF EXISTS " + TBL_FAVORITE);
        db.execSQL(" DROP TABLE IF EXISTS " + TBL_HISTORY);
        db.execSQL(" DROP TABLE IF EXISTS " + TBL_USER);
        onCreate(db);
    }
}
