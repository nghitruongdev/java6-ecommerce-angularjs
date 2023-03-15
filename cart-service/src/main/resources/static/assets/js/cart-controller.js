app.controller('cart-ctrl', function ($scope, $utility, $cart) {
    const {$serverUrl, $data} = $utility
    $scope.cart = $cart
    $scope.checkboxes = $cart.values.map((prod) => ({prodId: prod.id, isSelected: false}))
    $scope.addToCart = (prodId) => {
        if (checkQuantityOver10(prodId, $cart)) {
            return;
        }
        const url = `${$serverUrl.apiUrl}/products/${prodId}?projection=withCategory`
        console.log(url)
        $data.fetchOne(url, (resp) => {
            const product = resp.data
            const result = $cart.addItem({...product, _links: undefined})
            if (result)
                message("success", "Added Item", "Product has been added to cart.")
        })

    }

    $scope.changeQuantity = (prod, isPlus) => {
        if (isPlus) {
            if (checkQuantityOver10(prod.id, $cart)) {
                return;
            }
            $scope.addToCart(prod.id)
            return;
        }
        $cart.reduceQuantity(prod)
    }

    $scope.selectAll = (state) => {
        console.log('select all checkbox changed', state)
        $scope.checkboxes.forEach((item) => {
            item.isSelected = state
        })
        $scope.$apply()
    }

    $scope.removeItem = () => {
        angular.copy($scope.checkboxes).forEach((item, idx) => {
            if (item.isSelected) {
                $scope.cart.removeItem(item.prodId)
                $scope.checkboxes.splice(idx, 1)
            }
        })
    }

    $scope.isCheckboxSelected = () => {
        return $scope.checkboxes.some(item => item.isSelected)
    }
})

checkQuantityOver10 = (prodId, $cart) => {
    if ($cart.contains(prodId) && $cart.getItem(prodId).quantity === 10) {
        message('error', 'Failed', 'Không thể đặt nhiều hơn 10 sản phẩm cùng loại trong cùng một đơn hàng', true)
        return true;
    }
    return false;
}

function message(action, title, message, showConfirm) {
    Swal.fire({
        icon: action,
        title: title,
        html: message,
        showConfirmButton: showConfirm,
        timer: 1500
    })
}
