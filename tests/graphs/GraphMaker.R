chaining_table_data <- read.csv("./../Chaining_4.csv", sep = ";")
linear_probing_data <- read.csv("./../Linear_Probing_4.csv", sep = ";")
avl_tree_data <- read.csv("./../AVL_tree_4.csv", sep = ";")
splay_tree_data <- read.csv("./../Splay _tree_4.csv", sep = ";")

my_data <- data.frame(
  Size = chaining_table_data$Sample.Size, 
  SPLAY = splay_tree_data$CREATE, 
  AVL = avl_tree_data$CREATE, 
  LinearProbing = linear_probing_data$CREATE,
  Chaining = chaining_table_data$CREATE)
my_data

# Convert the data to long format using tidyr
my_data_long <- tidyr::pivot_longer(my_data, cols = c("SPLAY", "AVL", "LinearProbing", "Chaining"), names_to = "Structure", values_to = "Time")

# Create the graph using ggplot2
ggplot(my_data_long, aes(x = `Size`, y = Time, color = Structure)) + 
  geom_line() +
  labs(title = "Time taken for operations at different sample sizes", x = "Number of elements", y = "Time (nanoseconds)")