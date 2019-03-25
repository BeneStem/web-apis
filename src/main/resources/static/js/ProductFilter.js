class ProductFilter {

  static init() {
    const search = document.getElementById('search');
    search.addEventListener('click', ProductFilter.onClick);
  }

  static onClick() {
    const vegan = document.getElementById('vegan');
    const contains = document.getElementById('contains');

    ProductFilter.findProducts(vegan, contains)
      .then(ProductFilter.handleResponse);
  }

  static findProducts(vegan, contains) {
    return fetch(`http://localhost:8080/products?vegan=${vegan.checked}&contains=${contains.value}`, {
      method: 'get',
      headers: {
        Accept: 'application/de.dhbw.product+json'
      }
    });
  }

  static handleResponse(response) {
    response.json()
      .then(ProductFilter.refreshResult);
  }

  static refreshResult(products) {
    const result = document.getElementById('result');
    result.innerHTML = '';
    products.forEach((product) => {
      const productListItem = document.createElement('li');
      productListItem.innerHTML = `${product.name} vegan: ${product.vegan}`;
      result.appendChild(productListItem);
    });
  }
}

window.addEventListener('load', () => {
  ProductFilter.init();
});
