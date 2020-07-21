package com.example.dunzo.adapter;

import com.example.dunzo.model.PhotoListModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
public class PhotoGridAdapterTest {
    private PhotoGridAdapter adapter;

    PhotoListModel photo1 = new PhotoListModel();
    PhotoListModel photo2 = new PhotoListModel();

    @Before
    public void setUp() {
        List<PhotoListModel> photos = new ArrayList<>();
        photo1.setId("1");
        photo1.setUrl("http:\\www.com.example.dunzo");

        photo1.setId("2");
        photo1.setUrl("http:\\www.com.example.dunzo.in");

        photos.add(photo1);
        photos.add(photo2);


        adapter = new PhotoGridAdapter(photos);
    }

    @Test
    public void itemCount() {
        assertThat(adapter.getItemCount()).isEqualTo(2);
    }

    @Test
    public void getItemAtPosition() {
        assertThat(adapter.getItemByPosition(0)).isEqualTo(photo1);
        assertThat(adapter.getItemByPosition(1)).isEqualTo(photo2);
    }

}