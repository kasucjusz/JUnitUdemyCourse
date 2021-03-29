package pl.testingcourse.account;

public class Account {

    private boolean active;
    private Address defaultDeliveryAddress;

    public void setActive(boolean active) {
        this.active = active;
    }

    public Address getDefaultDeliveryAddress() {
        return defaultDeliveryAddress;
    }

    public void setDefaultDeliveryAddress(Address defaultDeliveryAddress) {
        this.defaultDeliveryAddress = defaultDeliveryAddress;
    }

    public void activate() {
        this.active = true;

    }

    public Account(Address defaultDeliveryAddress) {
        if(defaultDeliveryAddress!=null){
            activate();
        }
        else{
            this.active=false;
        }
        this.defaultDeliveryAddress = defaultDeliveryAddress;
    }

    public boolean isActive() {
        return this.active;
    }

    public Account() {
        this.active = false;
    }
}
