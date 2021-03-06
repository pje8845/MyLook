package com.ssu.mylook.dto;

/*계층간 데이터 교환을 위한 클래스 dto(data transfer object)*/

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import java.util.Map;

public class CoordiDTO implements Parcelable {
    private String name;
    private String img;
    private String regDate;
    private List<String> seasons;
    private List<String> used;
    //private String[] seasons;
    private String tag;
    //Map<String,Boolean> tag;
    private float rating;
    private int count;
    private String id;
    private String userId;


    //아직 수정중
//    private String title;
//    private String content;
    private int rank;

    public CoordiDTO() {
        super();
    }

    public CoordiDTO(String id, String name, String img, String regDate, List<String> seasons, String tag, float rating, int count, String userId, List<String> used) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.regDate = regDate;
        this.seasons = seasons;
        this.tag = tag;
        this.rating = rating;
        this.count = count;
        this.userId = userId;
        this.used = used;
    }

    protected CoordiDTO(Parcel in) {
        name = in.readString();
        img = in.readString();
        regDate = in.readString();
        seasons = in.createStringArrayList();
        used = in.createStringArrayList();
        tag = in.readString();
        rating = in.readFloat();
        count = in.readInt();
        id = in.readString();
        userId = in.readString();
        rank = in.readInt();
    }

    public static final Creator<CoordiDTO> CREATOR = new Creator<CoordiDTO>() {
        @Override
        public CoordiDTO createFromParcel(Parcel in) {
            return new CoordiDTO(in);
        }

        @Override
        public CoordiDTO[] newArray(int size) {
            return new CoordiDTO[size];
        }
    };

    public static CoordiDTO mapToDTO(Map<String, Object> data) {
        CoordiDTO coordiDTO = new CoordiDTO();
        coordiDTO.setImg((String) data.get("img"));
        coordiDTO.setName((String) data.get("name"));
        coordiDTO.setRating((float) data.get("rating"));
        coordiDTO.setRegDate((String) data.get("regDate"));
        coordiDTO.setTag((String) data.get("tag"));
        coordiDTO.setCount((int) data.get("count"));
        return coordiDTO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public List<String> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<String> seasons) {
        this.seasons = seasons;
    }

    public List<String> getUsed() {
        return used;
    }

    public void setUsed(List<String> used) {
        this.used = used;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(img);
        dest.writeString(regDate);
        dest.writeStringList(seasons);
        dest.writeStringList(used);
        dest.writeString(tag);
        dest.writeFloat(rating);
        dest.writeInt(count);
        dest.writeString(id);
        dest.writeString(userId);
        dest.writeInt(rank);
    }
}