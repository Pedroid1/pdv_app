package com.pedroid.pdv_app.presentation.create_order;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pedroid.pdv_app.domain.ValidationResult;
import com.pedroid.pdv_app.domain.model.Order;
import com.pedroid.pdv_app.domain.use_cases.order_repository.CreateOrderUseCase;
import com.pedroid.pdv_app.domain.use_cases.ui_validation.ValidateCustomerName;
import com.pedroid.pdv_app.domain.use_cases.ui_validation.ValidatePriceUseCase;
import com.pedroid.pdv_app.domain.use_cases.ui_validation.ValidateProductName;
import com.pedroid.pdv_app.domain.use_cases.ui_validation.ValidateQuantityUseCase;
import com.pedroid.pdv_app.presentation.utils.Event;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class CreateOrderViewModel extends ViewModel {
    private final CompositeDisposable disposables;
    private final CreateOrderUseCase createOrderUseCase;
    private final ValidateCustomerName validateCustomerName;
    private final ValidateProductName validateProductName;
    private final ValidateQuantityUseCase validateQuantityUseCase;
    private final ValidatePriceUseCase validatePriceUseCase;
    private final MutableLiveData<Event<FieldErrorValidation>> fieldErrorLiveData = new MutableLiveData<>();
    private final MutableLiveData<Event<Order>> orderCreated = new MutableLiveData<>();
    private final MutableLiveData<Event<String>> errorMessage = new MutableLiveData<>();

    @Inject
    public CreateOrderViewModel(
            CreateOrderUseCase createOrderUseCase,
            ValidateCustomerName validateCustomerName,
            ValidateProductName validateProductName,
            ValidateQuantityUseCase validateQuantityUseCase,
            ValidatePriceUseCase validatePriceUseCase) {
        this.createOrderUseCase = createOrderUseCase;
        this.validateCustomerName = validateCustomerName;
        this.validateProductName = validateProductName;
        this.validateQuantityUseCase = validateQuantityUseCase;
        this.validatePriceUseCase = validatePriceUseCase;
        disposables = new CompositeDisposable();
    }

    public void createOrder(String customerName, String product, String quantity, String price) {
        if (validateFields(customerName, product, quantity, price)) {
            Order order = new Order(null, customerName, product, Integer.parseInt(quantity), Double.parseDouble(price));
            disposables.add(
                    createOrderUseCase.execute(order)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    createdOrder -> orderCreated.setValue(new Event<>(createdOrder)),
                                    throwable -> errorMessage.setValue(new Event<>("Erro ao criar o pedido: " + throwable.getMessage()))
                            )
            );
        }
    }

    private boolean validateFields(String customerName, String product, String quantity, String price) {
        ValidationResult customerNameResult = validateCustomerName.execute(customerName);
        if (!customerNameResult.isSuccessful()) {
            FieldErrorValidation fieldErrorValidation = new FieldErrorValidation(CreateOrderFields.CUSTOMER_NAME, customerNameResult.getErrorMessage());
            fieldErrorLiveData.postValue(new Event<>(fieldErrorValidation));
            return false;
        }

        ValidationResult productNameResult = validateProductName.execute(product);
        if (!productNameResult.isSuccessful()) {
            FieldErrorValidation fieldErrorValidation = new FieldErrorValidation(CreateOrderFields.PRODUCT_NAME, productNameResult.getErrorMessage());
            fieldErrorLiveData.postValue(new Event<>(fieldErrorValidation));
            return false;
        }

        ValidationResult quantityResult = validateQuantityUseCase.execute(quantity);
        if (!quantityResult.isSuccessful()) {
            FieldErrorValidation fieldErrorValidation = new FieldErrorValidation(CreateOrderFields.QUANTITY, quantityResult.getErrorMessage());
            fieldErrorLiveData.postValue(new Event<>(fieldErrorValidation));
            return false;
        }

        ValidationResult priceResult = validatePriceUseCase.execute(price);
        if (!priceResult.isSuccessful()) {
            FieldErrorValidation fieldErrorValidation = new FieldErrorValidation(CreateOrderFields.PRICE, priceResult.getErrorMessage());
            fieldErrorLiveData.postValue(new Event<>(fieldErrorValidation));
            return false;
        }
        return true;
    }

    public MutableLiveData<Event<FieldErrorValidation>> getFieldErrorLiveData() {
        return fieldErrorLiveData;
    }

    public MutableLiveData<Event<Order>> getOrderCreated() {
        return orderCreated;
    }

    public MutableLiveData<Event<String>> getErrorMessage() {
        return errorMessage;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
