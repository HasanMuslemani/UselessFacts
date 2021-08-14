package com.hasanmuslemani.uselessfacts.facts;

public interface FactContract {

    interface View {
        void showLoading();
        void hideLoading();
        void showResult(String content);
        void showError(String message);
    }

    interface Presenter {
        void loadFact();
    }

}
