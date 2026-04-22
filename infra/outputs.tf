output "instance_name" {
  value = google_compute_instance.ndarray_demo.name
}

output "external_ip" {
  value = google_compute_instance.ndarray_demo.network_interface[0].access_config[0].nat_ip
}
