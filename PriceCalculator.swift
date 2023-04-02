func calculatePrice(quantity: Int, unitPrice: Double) -> Double {
  guard quantity >= 0 else {
    print("Invalid quantity!")
    return 0
  }
  return Double(quantity) * unitPrice
}

var priceItem = calculatePrice(quantity: -10, unitPrice: 1.99)
print("Item price is \(priceItem)\n")

var priceItem2 = calculatePrice(quantity: 0, unitPrice: 1.99)
print("Item price is \(priceItem2)\n")

var priceItem3 = calculatePrice(quantity: 10, unitPrice: 1.99)
print("Item price is \(priceItem3)\n")