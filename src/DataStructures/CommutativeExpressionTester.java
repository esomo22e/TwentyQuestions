package DataStructures;

/**
 * CommutativeExpressionTester.java
 * CS 211 
 * Audrey St. John
 **/

import ds.BinTree;


/**
 * Testing expression i/o.
 * @author Audrey Lee
 **/
public class CommutativeExpressionTester
{

  /**
   * Read in xml file in args[0], print tree, then write back to file at args[1].
   **/
  public static void main( String[] args )
  {
    BinTree<String> exprTree = CommutativeExpressionReader.readCommutativeExpr( args[0] );
    exprTree.inorderString();
    CommutativeExpressionWriter.writeCommutativeExpr( exprTree, args[1] );
  }
}
