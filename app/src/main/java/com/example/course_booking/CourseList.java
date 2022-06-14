package com.example.course_booking;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

//This class allow admin to add course name and course code and display as a list.

public class CourseList extends ArrayAdapter<CourseModel> {

    private Activity context;
    List<CourseModel> course;

    public CourseList(Activity context, List<CourseModel> course){

        //There are errors related to xml file probably because
        //I didn't connect the firebase to andoidStudio properly.

        super(context, R.layout.layout_product_list, course);
        this.context = context;
        this.course = course;
    }

    @Override
    public View getView(int position, View coverView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewCourse = inflater.inflate(R.layout.layout_product_list, null, true);

        TextView textViewCourseName = (TextView) listViewCourse.findViewById(R.id.textViewName);
        TextView textViewCourseId = (TextView) listViewCourse.findViewById(R.id.textViewId);

        CourseModel crs = course.get(position);
        textViewCourseName.setText(CourseModel.getCrsName());
        textViewCourseId.setText(CourseModel.getCrsCode());
        return listViewCourse;
    }

}
