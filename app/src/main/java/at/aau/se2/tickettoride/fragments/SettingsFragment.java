package at.aau.se2.tickettoride.fragments;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import at.aau.se2.tickettoride.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}
