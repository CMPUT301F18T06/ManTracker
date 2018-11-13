package project.ece301.mantracker.CareProviderHome;

public class CareProviderHomePresenter {
    private CareProviderHomeView careProviderHomeView;

    public CareProviderHomePresenter(CareProviderHomeView careProviderHomeView) {
        this.careProviderHomeView = careProviderHomeView;
    }

    public void onDestroy() {
        careProviderHomeView = null;
    }
}