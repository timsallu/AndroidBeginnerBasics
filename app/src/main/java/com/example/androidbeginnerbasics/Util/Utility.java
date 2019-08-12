package com.example.androidbeginnerbasics.Util;

import android.widget.Switch;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {

    public static String getCurrentTimeStamp()
    {
        try {
            SimpleDateFormat dateFormat=new SimpleDateFormat("MMM-yyyy");
            String currentdate=dateFormat.format(new Date());

            return currentdate;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getmonthinwords(String month)
    {

        switch(month)
        {
            case "01":{

                return "Jan";
            }

            case "02":{

                return "Feb";
            }

            case "03":{

                return "Mar";
            }

            case "04":{

                return "Apr";
            }

            case "05":{

                return "May";
            }

            case "06":{

                return "Jun";
            }

            case "07":{

                return "Jul";
            }

            case "08":{

                return "Aug";
            }

            case "09":{

                return "Oct";
            }

            case "10":{

                return "Sep";
            }

            case "11":{

                return "Nov";
            }

            case "12":{

                return "Dec";
            }

            default:
                return "Error";

        }
    }
}
