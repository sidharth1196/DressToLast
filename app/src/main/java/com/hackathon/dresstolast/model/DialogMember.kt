package com.hackathon.dresstolast.model

data class DialogMember(
    var title: String? = null,
    var body: String? = null,
    var cancellable: Boolean,
    var buttonPositiveText: String? = null,
    var buttonNegativeText: String? = null,
    var lambdaYes: () -> (Any) = {},
    var lambdaNo: () -> (Any) = {},
    var lambdaDismiss: () -> (Any) = {},
    var brand: Brand? = null,
    var reviewSum: Int? = null
)