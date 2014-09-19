package com.example.objects;

import android.os.Parcel;
import android.os.Parcelable;

public class EventObj implements Parcelable {
	
	private int mID;
	private String  mName;
	private String  mPlace;
	private String  mType;
	private String mcontactNO;
	private byte[] mImage;
	public EventObj(int id,String name,String place, String type, String contact, byte[] image)
	{
		mID = id;
		mName = name;
		mPlace = place;
		mType = type;
		mcontactNO = contact;
		mImage = image;
	}
	
	public String getEventName()
	{
		return mName;
	}
	
	public String getEventPlace()
	{
		return mPlace;
	}
	
	public String getEventType()
	{
		return mType;
	}
	public String getEventContact()
	{
		return mcontactNO;
	}
	public byte[] getImage()
	{
		return mImage;
	}
	public int getID()
	{
		return mID;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(mID);
		dest.writeString(mName);
		dest.writeString(mPlace);
		dest.writeString(mType);
		dest.writeString(mcontactNO);
		dest.writeInt(mImage.length); 
		dest.writeByteArray(mImage);
		
	}
	
	private EventObj(Parcel in){
		this.mID = in.readInt();
        this.mName = in.readString();
        this.mPlace = in.readString();
        this.mType = in.readString();
        this.mcontactNO = in.readString();
        mImage = new byte[in.readInt()]; 
        in.readByteArray(mImage);
    }
	
	public static final Parcelable.Creator<EventObj> CREATOR = new Parcelable.Creator<EventObj>() {
		 
        @Override
        public EventObj createFromParcel(Parcel source) {
            return new EventObj(source);
        }
 
        @Override
        public EventObj[] newArray(int size) {
            return new EventObj[size];
        }
    };

}
