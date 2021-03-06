package crafttweaker.api.item;

import crafttweaker.api.liquid.ILiquidStack;
import crafttweaker.api.player.IPlayer;

import java.util.*;

/**
 * Contains an ingredient stack. Is an ingredient with a specific stack size
 * assigned to it.
 *
 * @author Stan Hebben
 */
public class IngredientStack implements IIngredient {
    
    private final IIngredient ingredient;
    private final int amount;
    
    public IngredientStack(IIngredient ingredient, int amount) {
        this.ingredient = ingredient;
        this.amount = amount;
    }
    
    @Override
    public String getMark() {
        return ingredient.getMark();
    }
    
    @Override
    public int getAmount() {
        return amount;
    }
    
    @Override
    public List<IItemStack> getItems() {
        return ingredient.getItems();
    }
    
    @Override
    public IItemStack[] getItemArray() {
        IItemStack[] stacks = ingredient.getItemArray();
        for(int i = 0; i < stacks.length; i++) {
            stacks[i] = stacks[i].withAmount(getAmount());
        }
        return stacks;
    }
    
    @Override
    public List<ILiquidStack> getLiquids() {
        return Collections.emptyList();
    }
    
    @Override
    public IIngredient amount(int amount) {
        return new IngredientStack(ingredient, amount);
    }
    
    @Override
    public IIngredient transformNew(IItemTransformerNew transformer) {
        return new IngredientStack(ingredient.transformNew(transformer), amount);
    }
    
    @Override
    public IIngredient only(IItemCondition condition) {
        return new IngredientStack(ingredient.only(condition), amount);
    }
    
    @Override
    public IIngredient marked(String mark) {
        return new IngredientStack(ingredient.marked(mark), amount);
    }
    
    @Override
    public IIngredient or(IIngredient ingredient) {
        return new IngredientOr(this, ingredient);
    }
    
    @Override
    public boolean matches(IItemStack item) {
        return item != null && item.getAmount() >= this.getAmount() && ingredient.matches(item);
    }
    
    @Override
    public boolean matchesExact(IItemStack item) {
        return item != null && item.getAmount() >= this.getAmount() && ingredient.matchesExact(item);
    }
    
    @Override
    public boolean matches(ILiquidStack liquid) {
        return false;
    }
    
    @Override
    public boolean contains(IIngredient ingredient) {
        return this.ingredient.contains(ingredient);
    }
    
    @Override
    public IItemStack applyTransform(IItemStack item, IPlayer byPlayer) {
        return ingredient.applyTransform(item, byPlayer);
    }
    
    @Override
    public IItemStack applyNewTransform(IItemStack item) {
        return ingredient.applyNewTransform(item);
    }
    
    @Override
    public Object getInternal() {
        return ingredient;
    }
    
    @Override
    public String toCommandString() {
        return ingredient.toCommandString() + " * " + amount;
    }
    
    @Override
    public boolean hasNewTransformers() {
        return ingredient.hasNewTransformers();
    }
    
    @Override
    public boolean hasTransformers() {
        return ingredient.hasTransformers();
    }
    
    @Override
    public IIngredient transform(IItemTransformer transformer) {
        return new IngredientStack(ingredient.transform(transformer), amount);
    }
    
    // #############################
    // ### Object implementation ###
    // #############################
    
    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(",", "(Ingredients) ", "");
        for(IItemStack item : ingredient.getItems())
            stringJoiner.add(item.getName());
        return stringJoiner.toString();
    }
}
