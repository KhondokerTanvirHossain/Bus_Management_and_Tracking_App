package com.example.v.myapplicationlogin;

public class RouteOption {
    String mStart;
    String mStop;
    String mTime;
    String mGender;
    String mRoute;
    String mRouteID;
    String mBusID;
    String mSeat;
    RouteOption(String start, String stop, String time, String gender, String route, String route_id,String bus_id, String seat){
        mStart = start;
        mStop = stop;
        mTime = time;
        mGender = gender;
        mRoute = route;
        mRouteID = route_id;
        mBusID = bus_id;
        mSeat = seat;
    }
    String getStart(){
        return mStart;
    }
    String getStop(){
        return mStop;
    }
    String getmTime(){
        return mTime;
    }
    String getGender(){
        return mGender;
    }
    String getRoute(){
        return mRoute;
    }
    String getRouteID(){
        return mRouteID;
    }
    String getBusID(){
        return mBusID;
    }
    String getSeat(){
        return mSeat;
    }
}
