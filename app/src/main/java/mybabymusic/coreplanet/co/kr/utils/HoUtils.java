package mybabymusic.coreplanet.co.kr.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.format.DateFormat;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialog;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.TELEPHONY_SERVICE;

public class HoUtils {
    public static final String TAG = "HO, TAG RESULT : ";
    public static ProgressDialog dialogs;
    public static AppCompatDialog progressDialog;

    public static int dpToPx(Context ctx, float dp){
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,ctx.getResources().getDisplayMetrics());

        return px;
    }

    //인스타그램열기
    public static void goToInstagram(Context ctx, String id){
        Uri uri = Uri.parse("http://instagram.com/_u/"+id);
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.instagram.android");

        try {
            ctx.startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            ctx.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/"+id)));
        }
    }


    public static String setRemoveCloun(String a){
        if(HoUtils.isNull(a)){
            return "";
        }else{
            return a.substring(0,a.length()-2);
        }
    }

    public static String decodeEmoji (String message) {
        String myString= null;
        try {
            return URLDecoder.decode(message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return message;
        } catch (IllegalArgumentException e){
            return "";
        }
    }

    public static TextView setColorInPartitial(String pre_string, String string, String color, TextView textView) {
        SpannableStringBuilder builder = new SpannableStringBuilder(pre_string + string);
        builder.setSpan(new ForegroundColorSpan(Color.parseColor(color)), 0, pre_string.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.append(builder);
        return textView;

    }

    /*public static void setCustomToast(Activity act, String msg){
        LayoutInflater inflater = act.getLayoutInflater();
        View layout = inflater.inflate(R.layout.layout_custom_toast,(ViewGroup)act.findViewById(R.id.toast_layout));
        TextView text = layout.findViewById(R.id.tv_msg);
        Toast toast = new Toast(act);
        text.setText(msg);
        toast.setGravity(Gravity.BOTTOM,0,160);
        toast.setView(layout);
        toast.show();
    }*/



    public static String getAge(String a){
        String age[] = a.split("-");
        int res = 0;
        try {
            res = 2020- Integer.parseInt(age[0]);
        }catch (IndexOutOfBoundsException e){
            res = 2020-1950;
        }

        return Integer.toString(res)+"세";
    }

    //앱 버전가저오기
    public static String getAppVersion(Context context){
        String device_version = "";
        try {
            device_version = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return device_version;
    }

    public static void showProgressDialog(Activity activity, String sub){
        if(activity.isFinishing()){
            return;
        }
        dialogs = new ProgressDialog(activity);
        dialogs.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialogs.setCanceledOnTouchOutside(false);
        dialogs.setCancelable(false);
        if(!HoUtils.isNull(sub)){
            dialogs.setMessage(sub);
        }
        dialogs.show();

    }

    public static void hideProgressDialog(Activity activity){
        if(activity.isFinishing()){
            return;
        }

        if(dialogs.isShowing()){
            dialogs.dismiss();
        }
    }

    public static String convertDate() {
        return String.valueOf(DateFormat.format("yyyy-MM-dd HH:mm:ss", System.currentTimeMillis()));
    }

    public static String getSector(String a){
        if(HoUtils.isNull(a)){
            return "";
        }
        else{
            return a.replaceAll("s_apart","아파트").replaceAll("s_officetel","오피스텔").replaceAll("s_urban","도시형생활주택")
                    .replaceAll("s_hotel","호텔").replaceAll("s_store","상가/쇼핑몰").replaceAll("s_knowledge","지식산업센터").replaceAll("s_villa","빌라")
                    .replaceAll("s_land","토지").replaceAll("s_etc","기타").replaceAll(","," | ");
        }
    }

    public static String getType(String a){
        if(HoUtils.isNull(a)){
            return "신규";
        }else{
            if(a.equalsIgnoreCase("new")){
                return "신규";
            }else if(a.equalsIgnoreCase("exist")){
                return "기존";
            }else if(a.equalsIgnoreCase("change")){
                return "변경";
            }else{
                return "할인";
            }
        }
    }


    //통신사 가저오기
    public static String getTong(Context ctx) {
        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        String tong = "";
        String so = tm.getSimOperator();
        if (so != null && !so.trim().equals("")) {
            switch (Integer.parseInt(so)) {
                case 45005:
                case 45011:
                    tong = "0000"; // SKT
                    break;
                case 45002:
                case 45004:
                case 45008:
                    tong = "0001"; // KTF
                    break;
                case 45003:
                case 45006:
                    tong = "0002"; // LG U+
                    break;
            }
        }
        return tong;
    }

    //스태이터스바 색상
    public static void setStatusBarColor(Activity activity, int colorId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(colorId);
        }
    }

    //위, 경도 거리구하기
    public static String getDistance(double lat1, double lng1, double lat2, double lng2) {
        double distance;
        String res;
        Location locationA = new Location("point A");
        locationA.setLatitude(lat1);
        locationA.setLongitude(lng1);

        Location locationB = new Location("point B");
        locationB.setLatitude(lat2);
        locationB.setLongitude(lng2);

        distance = locationA.distanceTo(locationB);
        int a = (int) distance;
        if (a / 1000 != 0) {
            a = a / 1000;
            res = Integer.toString(a) + "km";
        } else {
            a = a / 1000;
            res = Integer.toString(a) + "km";
            //res = Integer.toString(a) + "m";
        }

        return res;
    }

    //시간변환하기
    public static String converLimitTime(String original, String type) {
        String time1 = original;
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", java.util.Locale.getDefault());
        Date date1 = null;
        try {
            date1 = dateFormat1.parse(time1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat dateFormat2 = new SimpleDateFormat(type, java.util.Locale.getDefault());
        String time2 = dateFormat2.format(date1);
        return time2;
    }

    private static class TIME_MAXIMUM
    {
        public static final int SEC = 60;
        public static final int MIN = 60;
        public static final int HOUR = 24;
        public static final int DAY = 30;
        public static final int MONTH = 12;
    }


    //채팅 시간 나타내기
    public static String calculateTime(Date date)
    {
        long curTime = System.currentTimeMillis();
        long regTime = date.getTime();
        long diffTime = (curTime - regTime) / 1000;
        String msg = null;

        if (diffTime < TIME_MAXIMUM.SEC)
        {
            // sec
            //msg = diffTime + "초전";
            msg = "오늘";
        }
        else if ((diffTime /= TIME_MAXIMUM.SEC) < TIME_MAXIMUM.MIN)
        {
            // min
            System.out.println(diffTime);

            //msg = diffTime + "분전";
            msg = "오늘";
        }
        else if ((diffTime /= TIME_MAXIMUM.MIN) < TIME_MAXIMUM.HOUR)
        {
            // hour
            //msg = (diffTime ) + "시간전";
            msg = "오늘";
        }
        else if ((diffTime /= TIME_MAXIMUM.HOUR) < TIME_MAXIMUM.DAY)
        {
            // day
            msg = (diffTime ) + "일전";
        }
        else if ((diffTime /= TIME_MAXIMUM.DAY) < TIME_MAXIMUM.MONTH)
        {
            // day
            msg = (diffTime ) + "달전";
        }
        else
        {
            msg = (diffTime) + "년전";
        }
        return msg;
    }

    //널값 체크
    public static boolean isNull(String s) {
        if (s == null || s.equals("") || s.equalsIgnoreCase("null"))
            return true;

        return false;
    }

    //키보드 내리기
    public static void hideKeyboard(Context context, EditText editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    //값이 이상할 경우 다른 값으로 대체 하는 함수
    public static String fixNull(String s1, String s2) {
        if (isNull(s1))
            return s2;

        return s1.trim();
    }

    //제이슨 값 받아오기
    public static String getStr(JSONObject jo, String key) {
        String s = null;
        try {
            if (jo.has(key)) {
                s = jo.getString(key);
            } else {
                s = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }


    // 금액 표시
    public static String currentpoint(String result) {

        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setGroupingSeparator(',');

        DecimalFormat df = new DecimalFormat("###,###,###,###");
        df.setDecimalFormatSymbols(dfs);

        try {
            if (!HoUtils.isNull(result)) {
                result = result.replaceAll(",", "");
                double inputNum = Double.parseDouble(result);
                result = df.format(inputNum).toString();
            } else {
                result = "";
            }
        } catch (NumberFormatException e) {
            // TODO: handle exception
        }

        return result;
    }


    //이메일 정규식
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    // 이메일 검사
    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    //비밀번호 정규식
    public static final Pattern VALID_PASSWOLD_REGEX_ALPHA_NUM = Pattern.compile("^(?=.*[A-Za-z])(?=.*[0-9])[a-zA-Z0-9]{6,12}$");
    // 6자리 ~ 12자리까지 가능 숫자문자 혼합
    // 비밀번호 검사
    public static boolean validatePassword(String pwStr) {
        Matcher matcher = VALID_PASSWOLD_REGEX_ALPHA_NUM.matcher(pwStr);
        //Log.d(HoUtils.TAG,"결과1 : "+matcher.matches());
        return matcher.matches();
    }

    //숫자만 입력했는지
    public static final Pattern VALID_ONLY_NUM = Pattern.compile("^[0-9]*$");

    public static boolean validateNumber(String pwStr) {
        Matcher matcher = VALID_ONLY_NUM.matcher(pwStr);
        return matcher.matches();
    }


    //imei 값 가저오기
    public static String getIMEI(Context ctx){
        TelephonyManager tm = (TelephonyManager)ctx.getSystemService(TELEPHONY_SERVICE);
        @SuppressLint("MissingPermission")
        String imei = tm.getDeviceId();

        return HoUtils.fixNull(imei,"0");
    }

    //단말기 모델명 가저오기
    public static String getDeviceName() {
        String strDeviceName = Build.MANUFACTURER + " " + Build.PRODUCT;

        return strDeviceName;
    }

    //imei 값 가저오기2
    public static String getDeviceId(Context ctx) {
//	   Log.i("TEST", "random > " + UUID.randomUUID().toString()); //ba123804-4454-48ea-b076-526fef515df1
        String id = Preferences.getPref(ctx, Preferences._DEVICEID);
        if(isNull(id)){
            @SuppressLint("MissingPermission")
            String newId = ((TelephonyManager)ctx.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
            if(isNull(newId)){
                newId = "35" +
                        Build.BOARD.length()%10+ Build.BRAND.length()%10 +
                        Build.CPU_ABI.length()%10 + Build.DEVICE.length()%10 +
                        Build.DISPLAY.length()%10 + Build.HOST.length()%10 +
                        Build.ID.length()%10 + Build.MANUFACTURER.length()%10 +
                        Build.MODEL.length()%10 + Build.PRODUCT.length()%10 +
                        Build.TAGS.length()%10 + Build.TYPE.length()%10 +
                        Build.USER.length()%10;
                if(isNull(newId)){
                    newId = UUID.randomUUID().toString();
                }
            }
            Preferences.setPref(ctx, Preferences._DEVICEID, newId);
            id = newId;
        }
        return id;
    }

}
