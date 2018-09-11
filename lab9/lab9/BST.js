function BST() {
    this.root = null;    
}

function Node(key, value) {
    this.key = key
    this.value = value
    this.left = null
    this.right = null
}

const get = (node, key) => {
    if(node === null) return null
    if(key < node.key){
        return get(node.left, key)
    } else if(key > node.key) {
        return get(node.right, key)
    } else {
        return node.value
    }
}

const put = (node, key, value) => {
    if(node === null) return new Node(key, value)
    if(key < node.key) {
        node.left = put(node.left, key, value)
    } else if(key > node.key) {
        node.right = put(node.right, key, value)
    } else {
        node.value = value
    }
    return node
}

const print = (node, level) => {
    if(node === null) return

    print(node.left, level + 1)
    console.log('-'.repeat(level) + node.key)
    print(node.right, level + 1)
}

const max = node => {
    if(node === null) return null
    if(node.right === null) return node
    return max(node.right)
}

const removeMax = node => {
    if(node === null) return null
    if(node.right === null) return node.left
    node.right = removeMax(node.right)
    return node
}

const remove = (node, key) => {
    if(node === null) return null

    if(key < node.key) {
        node.left = remove(node.left, key)
    } else if(key > node.key) {
        node.right = remove(node.right, key)
    } else {
        if(node.left === null) return node.right
        if(node.right === null) return node.left
        let temp = node
        node = max(node.left)
        node.left = removeMax(temp.left)
        node.right = temp.right
    }
    return node
}

BST.prototype.get = function(key) {
    return get(this.root, key)
}

BST.prototype.put = function(key, value) {
    this.root = put(this.root, key, value)
}

BST.prototype.remove = function(key) {
    if(key === null) return
    this.root = remove(this.root, key)
}

BST.prototype.print = function() {
    print(this.root, 0)
}

// BST.prototype[Symbol.iterator]


//-----------------------------------------------------------------------------------

function shuffleArray(array) {
    for (let i = array.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [array[i], array[j]] = [array[j], array[i]]; // eslint-disable-line no-param-reassign
    }
}

array = [...Array(15).keys()]
shuffleArray(array)

let btree = new BST()

array.forEach(e => btree.put(e, `${e}value`))

btree.print()

btree.remove(3)
console.log('-----------------')

btree.print()