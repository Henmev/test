
public interface Warehouse {

	/**
	 * Warehouse is an objects container. it contains: (1) a collection of repair tools (2)  a collection of repair materials. 
	 * This warehouse is the shared storage component where the different maintenance persons acquire
	 * their tools and materials.
	 * 
	 * @author Ran Arieli and chen mevashev
	 * 
	 */

		/**
		 * (This is a command.)
		 * 
	     * if(isTool) adds a new RepairTool tool with RepairTool.name = name, RepairTool.quantity = quantity, 
	     * else adds a new RepairMaterial with RepairMaterial.name = name, RepairMaterial.quantity = quantity 
	     * 
	     * @param Booolean isTool - true for RepairTool, false for RepairrMaterial
	     * @param String name
	     * @param int quantity
	     * @throws Exception if quantity<0
	     * @pre: none
	     * @post: if(isTool) isReapirToolsEmpty() == false.
	     * @post: if(not(isTool)) isRepairMaterialsEmpty() == false.
	     * @post: @post(QuantityOf(isTool,name)) == @pre(QuantityOf(isTool,name)) + quantity.
	     * @post: isContain(isTool,name) == true.
	     */
		void add(Boolean isTool, String name, int quantity) throws Exception;
		
		/**
		 * (This is a query.)
	     * checks whether there are RepairTools in the Warehouse.
	     *  
	     * @pre: none
	     * @return true if and only if there's at list one RepairTool in the Warehouse.
	     */
		Boolean isRepairToolsEmpty();
		
		/**
		 * (This is a query.)
	     * checks whether there are RepairTools in the Warehouse.
	     *  
	     * @pre: none
	     * @return true if and only if there's at list one RepairTool in the Warehouse.
	     */
		Boolean isRepairMaterialsEmpty();
		
		/**
		 * (This is a query.)
		 * 
	     * if(isTool) method checks if there is a RepairTool RT with RT.name == name in the Warehouse. 
	     * if(not(isTool)) method checks if there is a RepairMaterial RM with RM.name == name in the Warehouse.
	     * 
	     * @param Booolean isTool - true for RepairTool, false for RepairrMaterial
	     * @param String name
	     * @pre: none
	     * @return true if((isTool) && (there's a RepairTool RT with RT.name==name)) ||
	     * ((not(isTool)) && (there's a RepairMaterial RM with RM.name==name)) and false otherwise.   
	     */
		Boolean isContain(Boolean isTool, String name);
		
		/**
		 * (This is a query.)
		 * 
	     * (if(isTool)) returns current quantity of RepairTool RT such that RT.name==name   
	     * if(not(isTool)) returns current quantity of RepairMaterial RM such that RM.name==name
	     * 
	     * @param Booolean isTool - true for RepairTool, false for RepairrMaterial
	     * @param String name
	     * @pre: isContain(isTool,name)
	     * @throws Exception if(not(isContain(isTool,name)))
	     * @return RT.quantity with RT maintains RT.name==name (if(isTool)) 
	     * @return RM.quantity with RM maintains RM.name==name (is(not(isTool)))
	     */		
		int QuantityOf(Boolean isTool, String name) throws Exception;
		
		/**
		 * (This is a command.)
		 * 
	     * if(isTool) acquire amount of (quantity) units of RepairTool RT such that RT.name==name;
	     * if(not(isTool)) acquire amount of (quantity) units of RepairMaterial RM such that RM.name==name;
	     * 
	     * @param Booolean isTool - true for RepairTool, false for RepairrMaterial
	     * @param String name
	     * @param int quantity
	     * @pre: isContain(isTool,name)
	     * @throws Exception if(not(isContain(isTool,name)))
	     * @post: @post(QuantityOf(isTool,name)) + quantity == @pre((QuantityOf(isTool,name))
	     * @post: isContain(isTool,name) == true
	     */
		void acquire(Boolean isTool,String name,int quantity) throws Exception;
		
		/**
		 * (This is a command.)
		 * 
	     * if(isTool) release amount of (quantity) units of RepairTool RT such that RT.name==name;
	     * if(not(isTool)) release amount of (quantity) units of RepairMaterial RM such that RM.name==name;
	     * 
	     * @param Booolean isTool - true for RepairTool, false for RepairrMaterial
	     * @param String name
	     * @param int quantity
	     * @pre: isContain(isTool,name)
	     * @throws Exception if(not(isContain(isTool,name)))
	     * @post: @post(QuantityOf(isTool,name)) == @pre((QuantityOf(isTool,name)) + quantity
	     * @post: isContain(isTool,name) == true
	     */
		void release(Boolean isTool,String name,int quantity) throws Exception;
				
	}

	

