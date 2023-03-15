app.controller('checkout-ctrl', function ($scope, $utility, $cart) {
    const {$serverUrl, $http, $url} = $utility
    $scope.formData = {
        customer: loggedInCustomer,
        orderProducts: $cart.values,
    }
    console.log(loggedInCustomer)
    $scope.placeOrder = () => {
        console.log($scope.formData)
        const url = $serverUrl['checkout']
        $http.post(url, $scope.formData).then(resp => {
            if (resp.status === 201) {
                const orderId = resp.data
                placeOrderSuccess(orderId, $url, $cart)
                // alert("Bạn đã đặt hàng thành công!")
            }
            console.log(resp)
        }).catch(err => console.error(err))

    }
})

function placeOrderSuccess(orderId, $url, $cart) {
    let timerInterval
    Swal.fire({
        title: 'Your order has been successful!',
        icon: 'success',
        // html: 'You will be redirect in <b></b> sec.',
        timer: 2000,
        timerProgressBar: true,
        showConfirmButton: false,
        allowOutsideClick: false,
        allowEscapeKey: false,
        allowEnterKey: false,
        didOpen: () => {
            Swal.showLoading()
        },
        willClose: () => {
            clearInterval(timerInterval)
        }
    }).then((result) => {
        console.log(result)
        /* Read more about handling dismissals below */
        if (result.dismiss === Swal.DismissReason.timer) {
            console.log('I was closed by the timer')
            const url = `/orders/${orderId}`
            $cart.clear()
            $url.redirect(url)
        }
    })
}
