/* to store all global variables */


app.constant('GLOBAL_APP', {
	homeTplPath: 'views/home.html',
	sliderTplPath: 'views/slider/home-slider.html',
	homeProductTplPath: 'views/product/home-product.html',
    addReviewTplPath : 'views/product/addReviewModel.tpl.html',
	topRatedProductTplPath: 'views/other/top-rated-product.html',
	featuredProductTplPath: 'views/other/feature-product.html',
	recentViewProductTplPath: 'views/other/recent-view.html',
	loginTplPath: 'views/userauth/login.html',
    authTplPath: 'views/userauth/auth.dialog.html',
	signUpTplPath: 'views/userauth/signup.html',
	viewProductTplPath: 'views/product/product_view.html',
    cartViewTplPath: 'views/cart/cart-view.tpl.html',
    categoryTplPath: 'views/category/category.tpl.html',
    productreviewTplPath: 'views/review.html',
    checkoutTplPath: 'views/checkout/checkout.tpl.html',
    checkoutLoginTplPath: 'views/checkout/checkout.login.tpl.html',
    checkoutSignupTplPath: 'views/checkout/checkout.signup.tpl.html',
	checkoutAddressTplPath: 'views/checkout/checkout.address.tpl.html',
	checkoutPaymentTplPath: 'views/checkout/checkout.payment.tpl.html',
	/* admin templates */
	adminDashBoardTplPath: 'views/dashboard/admin_dashboard.tpl.html',
	dashboardOrdersTplPath: 'views/dashboard/orders.tpl.html',
	dashboardOrderViewTplPath: 'views/dashboard/order.tpl.html',
	dashboardPrductsTplPath: 'views/dashboard/products.tpl.html',
    dashboardPrductViewTplPath: 'views/dashboard/product.tpl.html',
    dashboardaddProductTplPath : 'views/dashboard/addProductModal.tpl.html',
    dashboardUMTplPath: 'views/dashboard/UserManagement/user_management.tpl.html',
    dashboardUMUsersTplPath: 'views/dashboard/UserManagement/users.tpl.html',
    //dashboardUMSuppliersTplPath: 'views/dashboard/UserManagement/suppliers.tpl.html',
    dashboardUMUserTplPath: 'views/dashboard/UserManagement/user.tpl.html',
    //dashboardUMSupplierTplPath: 'views/dashboard/UserManagement/supplier.tpl.html',
    reivewDialogTplPath : 'views/other/review.model.html',

    ordersTplPath: 'views/user/orders.tpl.html',
    orderDetailTplPath: 'views/user/orders.detail.tpl.html',
    settingTplPath: 'views/user/setting.tpl.html',

    ORDER_STATUS : ["PLACED","CANCLED","CANCLED_REVERSAL","CHARGE_BACK","COMPLETE","DENIED","EXPIRED","FILED","PENDING","PROCESSING","PROCESSED","REFUNDED","REVERSED","SHIPPED","VOIDED"],
    PAYMENT_METHOD : ['COD','PAYTM','DEBIT_CARD','CREDIT_CARD'],
});

