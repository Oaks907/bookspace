package com.tree.btree.RBTree;

import lombok.AllArgsConstructor;
import lombok.Data;
import sun.jvm.hotspot.utilities.RBNode;

/**
 * Create by haifei on 11/12/2018 4:52 PM.
 *
 * http://www.cnblogs.com/skywang12345/p/3624343.html
 *
 * (1) 每个节点或者是黑色，或者是红色。
 * (2) 根节点是黑色。
 * (3) 每个叶子节点是黑色。 [注意：这里叶子节点，是指为空的叶子节点！]
 * (4) 如果一个节点是红色的，则它的子节点必须是黑色的。
 * (5) 从一个节点到该节点的子孙节点的所有路径上包含相同数目的黑节点。
 */
@Data
public class RBTree<T extends Comparable<T>> {

  private RBTNode<T> root;
  private static final boolean RED = false;
  private static final boolean BLACK = true;

  @AllArgsConstructor
  @Data
  public class RBTNode<T extends Comparable<T>> {
    boolean color;
    T key;
    RBTNode<T> left;
    RBTNode<T> right;
    RBTNode<T> parent;
  }

  private RBTNode<T> parentOf(RBTNode<T> node) {
    return node != null ? node.parent : null;
  }

  private boolean isRed(RBTNode<T> node) {
    return node.color == RED;
  }

  private void leftRotate(RBTNode<T> x) {
    //设置 y 的右孩子为 y
    RBTNode<T> y = x.right;

    // 将 “y的左孩子” 设为 “x的右孩子”；
    // 如果y的左孩子非空，将 “x” 设为 “y的左孩子的父亲”
    x.right = y.left;
    if (y.left != null) {
      y.left.parent = x;
    }
    // 将 “x的父亲” 设为 “y的父亲”
    y.parent = x.parent;

    if (x.parent == null) {
      this.root = y;    // 如果 “x的父亲” 是空节点，则将y设为根节点
    } else {
      if (x.parent.left == x) {
        x.parent.left = y;  // 如果 x是它父节点的左孩子，则将y设为“x的父节点的左孩子”
      } else {
        x.parent.right = y;  // 如果 x是它父节点的左孩子，则将y设为“x的父节点的左孩子”
      }
    }

    // 将 “x” 设为 “y的左孩子”
    y.left = x;
    // 将 “x的父节点” 设为 “y”
    x.parent = y;
  }

  private void rightRotate(RBTNode<T> y) {
    // 设置x是当前节点的左孩子。
    RBTNode<T> x = y.left;

    // 将 “x的右孩子” 设为 “y的左孩子”；
    // 如果"x的右孩子"不为空的话，将 “y” 设为 “x的右孩子的父亲”
    y.left = x.right;
    if (x.right == null) {
      y.left.parent = x;
    }

    // 将 “y的父亲” 设为 “x的父亲”
    x.parent = y.parent;

    if (y.parent == null) {
      this.root = x;     // 如果 “y的父亲” 是空节点，则将x设为根节点
    } else {
      if (y.parent.left == y) {
        y.parent.left = x;// 如果 y是它父节点的右孩子，则将x设为“y的父节点的右孩子”
      } else {
        y.parent.right = x;// (y是它父节点的左孩子) 将x设为“x的父节点的左孩子”
      }
    }
    // 将 “y” 设为 “x的右孩子”
    x.right = y;
    // 将 “y的父节点” 设为 “x”
    y.parent = x;
  }

  /*
   * 将结点插入到红黑树中
   *
   * 参数说明：
   *     node 插入的结点        // 对应《算法导论》中的node
   */
  private void insert(RBTNode<T> node) {
    int cmp;
    RBTNode<T> y = null;
    RBTNode<T> x = this.root;

    // 1. 将红黑树当作一颗二叉查找树，将节点添加到二叉查找树中。
    while (x != null) {
      y = x;
      cmp = node.key.compareTo(y.key);
      if (cmp > 0) {
        x = x.right;
      } else {
        x = x.left;
      }
    }

    node.parent = y;

    if (y != null) {
      cmp = node.key.compareTo(y.key);
      if (cmp < 0) {
        y.left = node;
      } else {
        y.right = node;
      }
    } else {
      this.root = node;
    }
    // 2. 设置节点的颜色为红色
    node.color = RED;

    // 3. 将它重新修正为一颗二叉查找树
    insertFixUp(node);
  }

  /*
   * 新建结点(key)，并将其插入到红黑树中
   *
   * 参数说明：
   *     key 插入结点的键值
   */
  public void insert(T key) {
    RBTNode<T> node = new RBTNode<T>(BLACK, key, null, null, null);

    // 如果新建结点失败，则返回。
    if (node != null)
      insert(node);
  }

  /*
   * 红黑树插入修正函数
   *
   * 在向红黑树中插入节点之后(失去平衡)，再调用该函数；
   * 目的是将它重新塑造成一颗红黑树。
   *
   * 参数说明：
   *     node 插入的结点        // 对应《算法导论》中的z
   */
  private void insertFixUp(RBTNode<T> node) {
    RBTNode<T> parent, gparent;

    // 若“父节点存在，并且父节点的颜色是红色”
    while ((parent = parentOf(node)) != null && isRed(parent)) {
      gparent = parentOf(parent);

      //若“父节点”是“祖父节点的左孩子”
      if (parent == gparent.left) {
        // Case 1条件：叔叔节点是红色
        RBTNode<T> uncle = gparent.right;
        if (uncle != null && isRed(uncle)) {
          setBlack(uncle);
          setBlack(parent);
          setRed(gparent);
          node = gparent;
          continue;
        }
        // Case 2条件：叔叔是黑色，且当前节点是右孩子
        if (parent.right == node) {
          RBTNode<T> temp;
          leftRotate(parent);
          temp = parent;
          parent = node;
          node = temp;
        }

        // Case 3条件：叔叔是黑色，且当前节点是左孩子。
        setBlack(parent);
        setRed(gparent);
        rightRotate(gparent);
      } else {  //若“z的父节点”是“z的祖父节点的右孩子”
        // Case 1条件：叔叔节点是红色
        RBTNode<T> uncle = gparent.left;
        if (uncle != null && isRed(uncle)) {
          setBlack(uncle);
          setBlack(parent);
          setRed(gparent);
          node = gparent;
          continue;
        }

        // Case 2条件：叔叔是黑色，且当前节点是左孩子
        if (parent.left == node) {
          RBTNode<T> tmp;
          rightRotate(parent);
          tmp = parent;
          parent = node;
          node = tmp;
        }

        // Case 3条件：叔叔是黑色，且当前节点是右孩子。
        setBlack(parent);
        setRed(gparent);
        leftRotate(gparent);
      }
    }

    setBlack(this.root);
  }

  /*
   * 删除结点(node)，并返回被删除的结点
   *
   * 1。待删除的节点的兄弟节点是红色的节点。
   * 2。待删除的节点的兄弟节点是黑色的节点，且兄弟节点的子节点都是黑色的。
   * 3。 待调整的节点的兄弟节点是黑色的节点，且兄弟节点的左子节点是红色的，右节点是黑色的(兄弟节点在右边)，如果兄弟节点在左边的话，就是兄弟节点的右子节点是红色的，左节点是黑色的。
   * 4。 待调整的节点的兄弟节点是黑色的节点，且右子节点是是红色的(兄弟节点在右边)，如果兄弟节点在左边，则就是对应的就是左节点是红色的。
   *
   * 参数说明：
   *     node 删除的结点
   */
  private void remove(RBTNode<T> node) {
    RBTNode<T> child;
    RBTNode<T> parent;
    boolean color;

    // 被删除节点的"左右孩子都不为空"的情况。
    if (node.left != null && node.right != null) {
      // 被删节点的后继节点。(称为"取代节点")
      // 用它来取代"被删节点"的位置，然后再将"被删节点"去掉。
      RBTNode<T> replace = node;

      // 获取后继节点。删除节点的右节点的最左节点
      replace = replace.right;
      while (replace.left != null) {
        replace = replace.left;
      }

      // "node节点"不是根节点(只有根节点不存在父节点)
      if (parentOf(node) != null) {
        //要删除节点是左节点。使用replace节点替换要删除的节点
        if (parentOf(node).left == node) {
          parentOf(node).left = replace;
        } else {
          parentOf(node).right = replace;
        }
      } else {
        // "node节点"是根节点，更新根节点。
        this.root = replace;
      }

      // child是"取代节点"的右孩子，也是需要"调整的节点"。
      // "取代节点"肯定不存在左孩子！因为它是一个后继节点。
      child = replace.right;
      parent = parentOf(replace);

      // 保存"取代节点"的颜色
      color = colorOf(replace);

      // "被删除节点"是"它的后继节点的父节点"
      // 被删除节点的右子节点
      if (parent == node) {
        parent = replace;
      } else {
        if (child != null) {
          setParent(child, parent);
        }
        parent.left = child;
        replace.right = node.right;
        setParent(node.right, replace);
      }

      replace.parent = node.parent;
      replace.color = node.color;
      replace.left = node.left;
      node.left.parent = replace;

      if (color == BLACK) {
        removeFixUp(child, parent);
      }

      node = null;
      return;
    }

    if (node.left != null) {
      child = node.left;
    } else {
      child = node.right;
    }

    parent = node.parent;
    // 保存"取代节点"的颜色
    color = node.color;

    if (child != null) {
      child.parent = parent;
    }

    if (parent != null) {
      if (parent.left == node) {
        parent.left = child;
      } else {
        parent.right = child;
      }
    } else {
      this.root = child;
    }

    if (color == BLACK) {
      removeFixUp(child, parent);
    }

    node = null;
    return;
  }

  /*
   * 删除结点(z)，并返回被删除的结点
   *
   * 参数说明：
   *     tree 红黑树的根结点
   *     z 删除的结点
   */
  public void remove(T key) {
    RBTNode<T> node;

//    if ((node = search(mRoot, key)) != null)
//      remove(node);
  }

  /*
   * 红黑树删除修正函数
   *
   * 在从红黑树中删除插入节点之后(红黑树失去平衡)，再调用该函数；
   * 目的是将它重新塑造成一颗红黑树。
   *
   * 参数说明：
   *     node 待修正的节点
   */
  private void removeFixUp(RBTNode<T> node, RBTNode<T> parent) {
    RBTNode<T> other;

    while ((node == null || isBlack(node)) && node != this.root) {
      if (parent.left == node) {
        other = parent.right;
        if (isRed(other)) {
          // Case 1: x的兄弟w是红色的
          setBlack(other);
          setRed(parent);
          leftRotate(parent);
          other = parent.right;
        }

        // Case 2: x的兄弟w是黑色，且w的俩个孩子也都是黑色的
        if ((other.left == null || isBlack(other.left)) &&
          (other.right == null || isBlack(other.right))) {
          setRed(other);
          node = parent;
          parent = parentOf(node);
        } else {
          if (other.right == null || isBlack(other.right)) {
            // Case 3: x的兄弟w是黑色的，并且w的左孩子是红色，右孩子为黑色。
            setBlack(other.left);
            setRed(other);
            rightRotate(other);
            other = parent.right;
          }
          // Case 4: x的兄弟w是黑色的；并且w的右孩子是红色的，左孩子任意颜色。
          setColor(other, colorOf(parent));
          setBlack(parent);
          setBlack(other.right);
          leftRotate(parent);
          node = this.root;
          break;
        }
      } else {
        other = parent.left;
        if (isRed(other)) {
          // Case 1: x的兄弟w是红色的
          setBlack(other);
          setRed(parent);
          rightRotate(parent);
          other = parent.left;
        }

        if ((other.left==null || isBlack(other.left)) &&
          (other.right==null || isBlack(other.right))) {
          // Case 2: x的兄弟w是黑色，且w的俩个孩子也都是黑色的
          setRed(other);
          node = parent;
          parent = parentOf(node);
        } else {

          if (other.left==null || isBlack(other.left)) {
            // Case 3: x的兄弟w是黑色的，并且w的左孩子是红色，右孩子为黑色。
            setBlack(other.right);
            setRed(other);
            leftRotate(other);
            other = parent.left;
          }

          // Case 4: x的兄弟w是黑色的；并且w的右孩子是红色的，左孩子任意颜色。
          setColor(other, colorOf(parent));
          setBlack(parent);
          setBlack(other.left);
          rightRotate(parent);
          node = this.root;
          break;
        }
      }
    }

    if (node!=null){
      setBlack(node);
    }
  }


  public void setBlack(RBTNode<T> node) {
    node.color = BLACK;
  }

  public void setRed(RBTNode<T> node) {
    node.color = RED;
  }

  public boolean isBlack(RBTNode<T> node) {
    return node.color == BLACK;
  }

  public boolean colorOf(RBTNode<T> node) {
    return node.color;
  }

  private void setParent(RBTNode<T> node, RBTNode<T> parent) {
    if (node != null)
      node.parent = parent;
  }

  private void setColor(RBTNode<T> node, boolean color) {
    node.setColor(color);
  }

}
