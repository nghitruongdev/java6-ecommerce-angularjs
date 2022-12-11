app.controller('order-ctrl', function ($scope, $utility) {
    const {$serverUrl, $data, $url} = $utility
    const ordersByCustomerUrl = $serverUrl.api.ordersByCustomer(username)

    $data.fetchOne(ordersByCustomerUrl, (resp) => {
        console.log('fetch orders from', ordersByCustomerUrl)
        $scope.orders = resp.data._embedded.orders;
        console.log($scope.orders)
    })
    $scope.order = order

    $scope.viewDetail = (orderId) => {
        if ($scope.order && $scope.order.id === orderId) return
        const url = `/orders/${orderId}`
        $url.redirect(url)
    }
})
