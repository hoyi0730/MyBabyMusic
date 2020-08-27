package mybabymusic.coreplanet.co.kr.utils;

/**
 * Created by Administrator on 2017-06-23.
 */

public class JsonUrl {
    public static final String UTF_8 = "UTF-8";
    public static final String DEFAULT_HTTP_ADDRESS;
    public static final String SITEURL;
    public static final String CONNECTCODE;
    public static final String CONTROL;
    public static final String VERSIONCHECK; //버전체크
    public static final String APPINFO; //약관
    public static final String SMSSEND; // 인증번호전송
    public static final String CHECKAUTHJOIN; // 인증번호 인증하기
    public static final String REGISTERMEMBER; //회원가입
    public static final String FINDID; //아이디 찾기
    public static final String FINDPW; //패스워드 찾기
    public static final String LOGIN; //로그인
    public static final String MYSTOREMENUADD; //내상점메뉴추가
    public static final String MYSTOREMENULIST; //내메뉴리스트불러오기
    public static final String MYSTOREMENUMODIFY; //내상점메뉴수정
    public static final String MYSTOREMENUDEL; //메뉴삭제하기
    public static final String MYSTOREMENUIMGDEL; //메뉴 사진삭제하기
    public static final String MYSTOREMENUDETAIL; //메뉴 상세보기
    public static final String STORYREGIST; //스토리 추가
    public static final String STORYMODIFY; //스토리 수정
    public static final String STORYIMGDEL; //스토리 사진삭제
    public static final String MYSTORYLIST; //내 스토리 리스트 불러오기
    public static final String MYSTORYDEL; //내 스토리 삭제하기





    public static final String REGISTMEMBER; //회원가입
    public static final String EMAILSEND; //이메일 보내기
    public static final String CHECKEMAILAUTH; //이메일검사
    public static final String SETPROFILE; //회원정보수정
    public static final String SETPROFILEPIMG; //회원이미지수정
    public static final String GETPROFILEBASIC; //회원정보불러오기
    public static final String GETPROFILE; //상대방 회원프로필보기
    public static final String DECLAREMEMBER; //회원신고하기
    public static final String MEMBERLIST; //이성회원리스트 불러오기
    public static final String SETINTEREST; //관심회원등록하기
    public static final String TOINTERESTLIST; //관심회원리스트불러오기
    public static final String FROM_INTEREST_LIST; //나를 관심등록한 회원리스트 불러오기
    public static final String TALKREGISTINPUT; //토크작성하기
    public static final String TALKLIST; //토크리스트
    public static final String TALKDETAIL; //토크상세보기
    public static final String TALKCOMMENTINPUT; //토크댓글달기
    public static final String VIEWLIST; //공지사항
    public static final String SELLITEM; //회원포인트충전
    public static final String SETGIFT; //별풍선선물하기
    public static final String GETGIFT; //별풍선선물받은내역리스트
    public static final String EXCHANGEGIFT; //별풍선환전신청하기
    public static final String EXCHANGEGIFTLIST; //별풍선환전신청리스트
    public static final String EXCHANGEPOINT; //포인트현금환전신청하기
    public static final String EXCHANGEPOINTLIST; //포인트환전신청리스트
    public static final String ADLIST; //광고리스트
    public static final String CHKCHAT; //채팅방유무검사
    public static final String REQUESTCHAT; //일반채팅신청하기
    public static final String RESPONSECHAT; //일반채팅신청수락
    public static final String REJECTCHAT; //일반 채팅 거절
    public static final String FCMMSG; //채팅 푸시보내기
    public static final String ADDMSGIMG; //이미지업로드
    public static final String REQMOVIECALL; //영상채팅신청하기
    public static final String RESPMOVIECALL; //영상채팅 수신수락
    public static final String REJECTMOVIECALL; //영상채팅 수신거절
    public static final String SETMOVIECALLPOINT; //영상통화 포인트소진
    public static final String SETMOVIECALLSTATUS; //영상통화중으로 상태값바꾸기
    public static final String DISCONECTMOVIECALL; //영상통화 종료로 상태값바꾸기
    public static final String REQUESTPOINTCHAT; //포인트사용하여 채팅보내기
    public static final String LIST; //채팅리스트
    public static final String AUTOPAYOVER;// 정기결제 추가하기
    public static final String GIFTEDPOINTLIST; //포인트 선물받은내역
    public static final String SETMOVIEWCALLSTATUS; //회원상태값 영상통화중으로 바꾸기
    public static final String SETMOVIECALLREADYSTATUS; //회원상태값 영상통화중으로 통화중아님으로 바꾸기
    public static final String AUTOPAY; //정기결제 연장하기
    public static final String AUTOPAYCANCEL; //정기결제 취소하기
    public static final String GETCHKCHARGEPROFILE; //프로필 보기 횟수체크
    public static final String DROPMEMBER; //회원탈퇴하기
    public static final String TALKDEL; //토크삭제하기
    public static final String RESULT_Y = "Y";
    public static final String RESULT_N = "Y";
    public static final String EXCHANGEGIFTCANCEL;
    public static final String EXCHANGEPOINTCANCEL;
    public static final String MEMBERLISTNEW;
    public static final String FINDPWEMAILSEND;
    public static final String CHECKFINDPWEMAILAUTH;
    public static final String FINDPWREGI;
    public static final String SETGIFTINAPP;
    public static final String GETFRIENDCHK;
    public static final String SETSENDSTAR;
    public static final String SETSAVESTAR;
    public static final String LEAVECHAT;



    static {
        DEFAULT_HTTP_ADDRESS = "http://meshopcorp.adamstore.co.kr";
        CONTROL = DEFAULT_HTTP_ADDRESS+"/lib/control.siso";
        CONNECTCODE = "APP";
        SITEURL = "meshopcorp.adamstore.co.kr";
        VERSIONCHECK = DEFAULT_HTTP_ADDRESS+"/Main/version_chk";
        APPINFO = DEFAULT_HTTP_ADDRESS+"/Main/app_info";
        SMSSEND = DEFAULT_HTTP_ADDRESS+"/Member/sms_send";
        CHECKAUTHJOIN = DEFAULT_HTTP_ADDRESS+"/Member/check_auth_join";
        REGISTERMEMBER = DEFAULT_HTTP_ADDRESS+"/Member/regist_member";
        FINDID = DEFAULT_HTTP_ADDRESS+"/Member/findid";
        FINDPW = DEFAULT_HTTP_ADDRESS+"/Member/find_pw";
        LOGIN = DEFAULT_HTTP_ADDRESS+"/Member/login";
        MYSTOREMENUADD = DEFAULT_HTTP_ADDRESS+"/Member/mystoremenuadd";
        MYSTOREMENULIST = DEFAULT_HTTP_ADDRESS+"/Member/mystoremenulist";
        MYSTOREMENUMODIFY = DEFAULT_HTTP_ADDRESS+"/Member/mystoremenumodify";
        MYSTOREMENUDEL = DEFAULT_HTTP_ADDRESS+"/Member/mystoremenudel";
        MYSTOREMENUIMGDEL = DEFAULT_HTTP_ADDRESS+"/Member/mystoremenuimgdel";
        MYSTOREMENUDETAIL = DEFAULT_HTTP_ADDRESS+"/Member/mystoremenudetail";
        STORYREGIST = DEFAULT_HTTP_ADDRESS+"/Board/story_regist";
        STORYMODIFY = DEFAULT_HTTP_ADDRESS+"/Board/story_modify";
        STORYIMGDEL = DEFAULT_HTTP_ADDRESS+"/Board/story_img_del";
        MYSTORYLIST = DEFAULT_HTTP_ADDRESS+"/Board/mystorylist";
        MYSTORYDEL = DEFAULT_HTTP_ADDRESS+"/Board/mystorydel";




        REGISTMEMBER = DEFAULT_HTTP_ADDRESS+"/Member/regist_member";
        EMAILSEND = DEFAULT_HTTP_ADDRESS+"/Member/email_send";
        CHECKEMAILAUTH = DEFAULT_HTTP_ADDRESS+"/Member/check_email_auth";
        SETPROFILE = DEFAULT_HTTP_ADDRESS+"/Member/setProfile";
        SETPROFILEPIMG = DEFAULT_HTTP_ADDRESS+"/Member/setProfilepimg";
        GETPROFILEBASIC = DEFAULT_HTTP_ADDRESS+"/Member/getProfileBasic";
        GETPROFILE = DEFAULT_HTTP_ADDRESS+"/Member/getProfile";
        DECLAREMEMBER = DEFAULT_HTTP_ADDRESS+"/Member/declaremember";
        MEMBERLIST = DEFAULT_HTTP_ADDRESS+"/Member/memberlist";
        SETINTEREST = DEFAULT_HTTP_ADDRESS+"/Member/set_interest";
        TOINTERESTLIST = DEFAULT_HTTP_ADDRESS+"/Member/to_interest_list";
        FROM_INTEREST_LIST = DEFAULT_HTTP_ADDRESS+"/Member/from_interest_list";
        TALKREGISTINPUT = DEFAULT_HTTP_ADDRESS+"/Board/talk_regist_input";
        TALKLIST = DEFAULT_HTTP_ADDRESS+"/Board/talk_list";
        TALKDETAIL = DEFAULT_HTTP_ADDRESS+"/Board/talk_detail";
        TALKCOMMENTINPUT = DEFAULT_HTTP_ADDRESS+"/Board/talk_comment_input";

        VIEWLIST = DEFAULT_HTTP_ADDRESS+"/Board/view_list";
        SELLITEM = DEFAULT_HTTP_ADDRESS+"/Member/sell_item";
        SETGIFT = DEFAULT_HTTP_ADDRESS+"/Member/set_gift";
        GETGIFT = DEFAULT_HTTP_ADDRESS+"/Member/get_gift";
        EXCHANGEGIFT = DEFAULT_HTTP_ADDRESS+"/Member/exchange_gift";
        EXCHANGEGIFTLIST = DEFAULT_HTTP_ADDRESS+"/Member/exchage_gift_list";
        EXCHANGEPOINT = DEFAULT_HTTP_ADDRESS+"/Member/exchange_point";
        EXCHANGEPOINTLIST = DEFAULT_HTTP_ADDRESS+"/Member/exchange_point_list";
        ADLIST = DEFAULT_HTTP_ADDRESS+"/Board/ad_list";
        CHKCHAT = DEFAULT_HTTP_ADDRESS+"/Chat/chkChat";
        REQUESTCHAT = DEFAULT_HTTP_ADDRESS+"/Chat/requestChat";
        RESPONSECHAT = DEFAULT_HTTP_ADDRESS+"/Chat/responseChat";
        REJECTCHAT = DEFAULT_HTTP_ADDRESS+"/Chat/rejectChat";
        FCMMSG = DEFAULT_HTTP_ADDRESS+"/Chat/fcmMsg";
        ADDMSGIMG = DEFAULT_HTTP_ADDRESS+"/Chat/addmsgimg";
        REQMOVIECALL = DEFAULT_HTTP_ADDRESS+"/Chat/reqMovieCall";
        RESPMOVIECALL = DEFAULT_HTTP_ADDRESS+"/Chat/respMovieCall";
        REJECTMOVIECALL = DEFAULT_HTTP_ADDRESS+"/Chat/rejectMovieCall";
        SETMOVIECALLPOINT = DEFAULT_HTTP_ADDRESS+"/Chat/setMovieCallPoint";
        SETMOVIECALLSTATUS = DEFAULT_HTTP_ADDRESS+"/Chat/setMovieCallStatus";
        DISCONECTMOVIECALL = DEFAULT_HTTP_ADDRESS+"/Chat/disconectMovieCall";
        LIST  = DEFAULT_HTTP_ADDRESS+"/Chat/listChats";
        REQUESTPOINTCHAT = DEFAULT_HTTP_ADDRESS+"/Chat/requestPointChat";
        AUTOPAYOVER = DEFAULT_HTTP_ADDRESS+"/Member/autopay_over";
        GIFTEDPOINTLIST = DEFAULT_HTTP_ADDRESS+"/Member/gifted_point_list";
        SETMOVIEWCALLSTATUS = DEFAULT_HTTP_ADDRESS+"/Chat/setMovieCallStatus";
        SETMOVIECALLREADYSTATUS = DEFAULT_HTTP_ADDRESS+"/Chat/setMovieCallReadyStatus";
        AUTOPAY = DEFAULT_HTTP_ADDRESS+"/Member/autopay";
        AUTOPAYCANCEL = DEFAULT_HTTP_ADDRESS+"/Member/autopay_cancel";
        GETCHKCHARGEPROFILE = DEFAULT_HTTP_ADDRESS+"/Member/getChkChargeProfile";
        DROPMEMBER = DEFAULT_HTTP_ADDRESS+"/Member/drop_member";
        TALKDEL = DEFAULT_HTTP_ADDRESS+"/Board/talk_del";
        EXCHANGEGIFTCANCEL = DEFAULT_HTTP_ADDRESS+"/Member/exchange_gift_cancel";
        EXCHANGEPOINTCANCEL = DEFAULT_HTTP_ADDRESS+"/Member/exchange_point_cancel";
        MEMBERLISTNEW = DEFAULT_HTTP_ADDRESS+"/Member/memberlist_new";
        FINDPWEMAILSEND = DEFAULT_HTTP_ADDRESS+"/Member/findpw_email_send";
        CHECKFINDPWEMAILAUTH = DEFAULT_HTTP_ADDRESS+"/Member/check_findpw_email_auth";
        FINDPWREGI = DEFAULT_HTTP_ADDRESS+"/Member/findpw_regi";
        SETGIFTINAPP = DEFAULT_HTTP_ADDRESS+"/Member/set_gift_inapp";
        GETFRIENDCHK = DEFAULT_HTTP_ADDRESS+"/Member/getFriendChk";
        SETSENDSTAR = DEFAULT_HTTP_ADDRESS+"/Member/setSendStar";
        SETSAVESTAR = DEFAULT_HTTP_ADDRESS+"/Member/setSaveStar";
        LEAVECHAT = DEFAULT_HTTP_ADDRESS+"/Chat/leaveChat";

    }
}
