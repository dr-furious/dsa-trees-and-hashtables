my_data <- data.frame(
  Size = ch_full$Sample.Size, 
  Splay = splay_full_for_all_cmp$DELETE, 
  AVL = avl_full_for_all_cmp$DELETE, 
  LinearProbing = lp_full$DELETE,
  Chaining = ch_full$DELETE)
my_data

# Convert the data to long format using tidyr
my_data_long <- tidyr::pivot_longer(my_data, cols = c("Splay", "AVL", "LinearProbing", "Chaining"), names_to = "Structure", values_to = "Time")

# Getting the greatest size value
max_x_size <- chaining_table_data$Sample.Size[length(chaining_table_data$Sample.Size)]
max_y_size <- max(my_data$Splay, my_data$AVL, my_data$LinearProbing, my_data$Chaining)


# Create the graph using ggplot2
ggplot(my_data_long, aes(x = `Size`, y = Time, color = Structure)) + 
  geom_line() +
  geom_vline(xintercept = 0, color = "#111111") + # add a vertical line at x = 0
  geom_hline(yintercept = 0, color = "#111111") + # add a horizontal line at y = 0
  theme(panel.background = element_rect(fill = "#eeeeee"), panel.grid.major = element_line(color = "#ffffff"), panel.grid.minor = element_line(color = "#ffffff"), panel.border = element_rect(color = "#111111", fill = "transparent")) +
  labs(title = "Time taken for deletion of all data structures", x = "Number of nodes", y = "Time (ns)") +
  scale_x_continuous(breaks = seq(0, max_x_size, length.out = 11), labels = scales::comma_format()) + # specify 20 tick marks on x-axis
  scale_y_continuous(breaks = seq(0, round(max_y_size, -3), length.out = 11), labels = scales::comma_format()) # specify 20 tick marks on y-axis and format labels as comma-separated values