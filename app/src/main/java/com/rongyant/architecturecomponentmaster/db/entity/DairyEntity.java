package com.rongyant.architecturecomponentmaster.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import com.rongyant.architecturecomponentmaster.model.Dairy;

import java.util.Date;

/**
 *
 * Created by XRY on 2017/7/19.
 */
@Entity(tableName = "dairies", primaryKeys = {"userId", "diaryId"},
    foreignKeys = {
            @ForeignKey(entity = UserEntity.class,
                parentColumns = "userId",
                childColumns = "userId",
                onDelete = ForeignKey.CASCADE)
    })
public class DairyEntity implements Dairy {
    private int userId;
    private int diaryId;
    private String dairyContent;
    private Date date;

    public DairyEntity(Dairy dairy) {
        this.userId = dairy.getUserId();
        this.diaryId = dairy.getDiaryId();
        this.dairyContent = dairy.getDairyContent();
        this.date = dairy.getDate();
    }

    public DairyEntity() {
    }

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public int getDiaryId() {
        return diaryId;
    }

    @Override
    public String getDairyContent() {
        return dairyContent;
    }

    @Override
    public Date getDate() {
        return date;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDiaryId(int diaryId) {
        this.diaryId = diaryId;
    }

    public void setDairyContent(String dairyContent) {
        this.dairyContent = dairyContent;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DairyEntity entity = (DairyEntity) o;

        if (userId != entity.userId) return false;
        if (diaryId != entity.diaryId) return false;
        if (dairyContent != null ? !dairyContent.equals(entity.dairyContent) : entity.dairyContent != null)
            return false;
        return date != null ? date.equals(entity.date) : entity.date == null;

    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + diaryId;
        result = 31 * result + (dairyContent != null ? dairyContent.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DairyEntity{" +
                "userId=" + userId +
                ", diaryId=" + diaryId +
                ", dairyContent='" + dairyContent + '\'' +
                ", date=" + date +
                '}';
    }
}
